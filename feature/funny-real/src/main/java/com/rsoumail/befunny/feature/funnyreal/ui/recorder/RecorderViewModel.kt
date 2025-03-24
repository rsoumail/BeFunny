package com.rsoumail.befunny.feature.funnyreal.ui.recorder

import android.annotation.SuppressLint
import android.media.MediaMetadataRetriever
import android.media.MediaMetadataRetriever.METADATA_KEY_DURATION
import android.os.CountDownTimer
import androidx.camera.core.CameraSelector
import androidx.camera.video.FileOutputOptions
import androidx.camera.video.Recording
import androidx.camera.video.VideoRecordEvent.Finalize
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.video.AudioConfig
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsoumail.befunny.feature.funnyreal.domain.repository.FileRepository
import com.rsoumail.befunny.feature.funnyreal.domain.usecase.GetFunniesUseCase
import com.rsoumail.befunny.feature.funnyreal.ui.common.model.Funny
import com.rsoumail.befunny.feature.funnyreal.ui.common.model.MetaData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import kotlin.time.DurationUnit
import kotlin.time.toDuration

const val FUNNY_TIME_LIMIT = 2000

class RecorderViewModel(
    private val fileRepository: FileRepository,
    private val getFunniesUseCase: GetFunniesUseCase,
    private val controller: LifecycleCameraController,
    private val executor: Executor
) : ViewModel() {

    private val _funnies = MutableStateFlow<List<Funny>>(listOf())
    val funnies = _funnies.asStateFlow()

    private val _remainingTime = MutableStateFlow("")
    val remainingTime = _remainingTime.asStateFlow()

    var recording: Recording? = null
    private val _isRecording = MutableStateFlow(false)
    val isRecording = _isRecording.asStateFlow()

    private val mediaMetadataRetriever = MediaMetadataRetriever()

    val tenSec = 10 * FUNNY_TIME_LIMIT

    private val timer = object : CountDownTimer(tenSec.toLong(), 1000) {
        override fun onTick(millisUntilFinished: Long) {
            val secondsRemaining = millisUntilFinished / 1000
            val remainingSecondString = if (secondsRemaining.toInt() == 0) {
                ""
            } else {
                String.format("%02d", secondsRemaining)
            }
            _remainingTime.value = remainingSecondString
            println("Seconds remaining: $secondsRemaining")
        }

        override fun onFinish() {
            recording?.stop()
        }
    }


    fun loadFunnies() {
        viewModelScope.launch(Dispatchers.IO) {
            getFunniesUseCase().stateIn(viewModelScope).collect { domainFunnies ->
                val funnies = arrayListOf<Funny>()
                domainFunnies.map { funny ->
                    funnies.add(
                        Funny(
                            id = funny.id,
                            location = funny.location,
                            likes = funny.likes,
                            getMetaData(funny.location)
                        )
                    )
                }
                _funnies.update { funnies }
            }
        }
    }

    private fun getMetaData(fileLocation: String): MetaData {
        mediaMetadataRetriever.setDataSource(fileLocation)

        var durationString = ""

        mediaMetadataRetriever.extractMetadata(METADATA_KEY_DURATION)?.let {
            val duration = it.toLong().toDuration(DurationUnit.MILLISECONDS)
            val minutes = duration.toComponents { _, mins, _, _ -> mins }
            val seconds = duration.toComponents { _, _, secs, _ -> secs }
            durationString = "${minutes}:${String.format("%02d", seconds)}"
        }

        return MetaData(
            thumbs = mediaMetadataRetriever.frameAtTime,
            duration = durationString
        )
    }

    fun getController(): LifecycleCameraController {
        return controller
    }

    @SuppressLint("MissingPermission")
    fun record(onRecordFinnish: (String) -> Unit) {
        if (recording != null) {
            recording?.stop()

            _isRecording.value = false
            recording = null
            return
        }

        _isRecording.value = true

        viewModelScope.launch {
            fileRepository
                .create("funny_${System.currentTimeMillis()}.mp4")
                .let {
                    _remainingTime.value = (FUNNY_TIME_LIMIT / 1000).toString()
                    timer.start()
                    recording = controller.startRecording(
                        FileOutputOptions.Builder(it).build(),
                        // Todo Manage permission
                        AudioConfig.create(true),
                        executor
                    ) { event ->
                        if (event is Finalize) {
                            recording?.close()
                            timer.cancel()
                            _isRecording.value = false
                            recording = null
                            onRecordFinnish(it.path)
                            _remainingTime.value = ""
                        }
                    }

                }
        }
    }

    fun switch() {
        controller.cameraSelector =
            if (controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA)
                CameraSelector.DEFAULT_FRONT_CAMERA
            else CameraSelector.DEFAULT_BACK_CAMERA
    }

    override fun onCleared() {
        super.onCleared()
        controller.unbind()
    }
}