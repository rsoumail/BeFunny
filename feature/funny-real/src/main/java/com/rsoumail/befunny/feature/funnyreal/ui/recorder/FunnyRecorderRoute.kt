package com.rsoumail.befunny.feature.funnyreal.ui.recorder

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel

@Composable
fun FunnyRecorderRoute(
    navigateToPlayer: (String) -> Unit,
    funnyRecorderViewModel: FunnyRecorderViewModel = koinViewModel()
) {
    FunnyRecorderScreen(
        navigateToPlayer = navigateToPlayer,
        getFileRecorder = {
            funnyRecorderViewModel.getFileRecorder()
        }
    )
}