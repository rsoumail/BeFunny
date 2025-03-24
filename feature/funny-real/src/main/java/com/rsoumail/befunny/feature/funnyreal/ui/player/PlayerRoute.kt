package com.rsoumail.befunny.feature.funnyreal.ui.player

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.rsoumail.befunny.feature.funnyreal.ui.common.model.Funny
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayerRoute(
    funnyUrl: String,
    onCancelNavigation: () -> Unit,
    onConfirmChallenge: () -> Unit,
    playerViewModel: PlayerViewModel = koinViewModel(),
    isFromRecorder: Boolean = true
) {
    LaunchedEffect(playerViewModel) {
        playerViewModel.addVideoLocation(funnyUrl)
    }

    PlayerScreen(
        funny = Funny(location = funnyUrl),
        player = playerViewModel.player(),
        onCancelNavigateTo = { onCancelNavigation() },
        publishFunny = { funny ->
            playerViewModel.publishFunny(funny = funny)
        },
        onCancel = { funny ->
            playerViewModel.cancel(funny.location)
        },
        voteOnFunny = { funny ->
            playerViewModel.vote(funny)
        },
        onConfirmChallenge = onConfirmChallenge,
        isFromRecorder = isFromRecorder
    )
}