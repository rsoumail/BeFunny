package com.rsoumail.befunny.feature.funnyreal.ui.recorder

import androidx.compose.runtime.Composable

@Composable
fun FunnyRecorderRoute(
    navigateToPlayer: (String) -> Unit
) {
    FunnyRecorderScreen(
        navigateToPlayer = navigateToPlayer
    )
}