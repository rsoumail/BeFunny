package com.rsoumail.befunny.feature.funnyreal.ui.recorder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecorderRoute(
    navigateToPlayer: (String) -> Unit,
    recorderViewModel: RecorderViewModel = koinViewModel()
) {
    RecorderScreen(
        funnies = recorderViewModel.funnies.collectAsState().value,
        record = {
            recorderViewModel.record { navigateToPlayer(it) }
        },
        switchCamera = { recorderViewModel.switch() },
        isVideoRecording = recorderViewModel.isRecording.collectAsState().value,
        onOpenGallery = { recorderViewModel.loadFunnies() },
        remainingTime = recorderViewModel.remainingTime.collectAsState().value,
        controller = recorderViewModel.getController(),
    )
}