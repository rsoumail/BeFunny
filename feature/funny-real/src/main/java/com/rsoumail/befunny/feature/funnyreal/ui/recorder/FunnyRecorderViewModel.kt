package com.rsoumail.befunny.feature.funnyreal.ui.recorder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsoumail.befunny.feature.funnyreal.domain.repository.FileRepository
import kotlinx.coroutines.launch
import java.io.File

class FunnyRecorderViewModel(
    private val fileRepository: FileRepository
) : ViewModel() {

    suspend fun getFileRecorder(): File {
        return fileRepository
            .create("funny_${System.currentTimeMillis()}.mp4")
    }
}