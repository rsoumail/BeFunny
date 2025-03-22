package com.rsoumail.befunny.feature.funnyreal.ui.player

import androidx.compose.runtime.Composable
import com.rsoumail.befunny.feature.funnyreal.ui.common.model.Funny
import org.koin.androidx.compose.koinViewModel

@Composable
fun FunnyPlayerRoute(
    funnyUrl: String,
    onCancelNavigation: () -> Unit,
    funnyPlayerViewModel: FunnyPlayerViewModel = koinViewModel()
) {
    FunnyPlayerScreen(
        funny = Funny(location = funnyUrl),
        player = funnyPlayerViewModel.player(),
        onCancelNavigateTo = { onCancelNavigation() },
        publishFunny = { funny ->
            funnyPlayerViewModel.publishFunny(funny = funny)
        },
        onCancel = { funny ->
            funnyPlayerViewModel.cancel(funny.location)
        }
    )
}