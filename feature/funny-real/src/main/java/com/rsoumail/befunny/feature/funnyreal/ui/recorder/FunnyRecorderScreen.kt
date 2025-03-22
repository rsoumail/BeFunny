package com.rsoumail.befunny.feature.funnyreal.ui.recorder

import android.annotation.SuppressLint
import androidx.camera.core.CameraSelector
import androidx.camera.video.FileOutputOptions
import androidx.camera.video.Recording
import androidx.camera.video.VideoRecordEvent.Finalize
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.video.AudioConfig
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import kotlinx.coroutines.launch
import java.io.File

const val FUNNY_TIME_LIMIT = 3000

@SuppressLint("MissingPermission")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FunnyRecorderScreen(
    navigateToPlayer: (String) -> Unit
) {

    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var recording: Recording? = null
    var isRecording by rememberSaveable { mutableStateOf(false) }

    val tenSec = 11 * FUNNY_TIME_LIMIT

    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.VIDEO_CAPTURE)
        }
    }

    val onGalleryClick = {
        scope.launch {
            scaffoldState.bottomSheetState.expand()
        }
    }

    val onSwitchCameraClick = {
        controller.cameraSelector =
            if (controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA)
                CameraSelector.DEFAULT_FRONT_CAMERA
            else CameraSelector.DEFAULT_BACK_CAMERA
    }

    val onRecordClicked = onRecordClicked@{
        if (recording != null) {
            recording?.stop()
            isRecording = false
            recording = null
            return@onRecordClicked
        }

        isRecording = true
        val file = File(context.cacheDir, "temp-record-video.mp4")
        recording = controller.startRecording(
            FileOutputOptions.Builder(file).build(),
            // Todo Manage permission
            AudioConfig.create(true),
            ContextCompat.getMainExecutor(context.applicationContext)
        ) { event ->
            if (event is Finalize) {
                recording?.close()
                isRecording = false
                recording = null
                navigateToPlayer(file.path)
            }
        }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {

        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)

        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(500.dp)
                    .clip(RoundedCornerShape(15.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CameraPreview(controller)
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RecorderController(
                    onRecordClick = onRecordClicked,
                    onSwitchCameraClick = onSwitchCameraClick,
                    onGalleryClick = { onGalleryClick() }
                )
            }
        }
    }
}