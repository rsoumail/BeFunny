package com.rsoumail.befunny.feature.funnyreal.ui.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.ExoPlayer
import com.rsoumail.befunny.feature.funnyreal.ui.common.model.Funny

@Composable
fun PlayerScreen(
    funny: Funny,
    player: ExoPlayer,
    onCancel: (Funny) -> Unit,
    onCancelNavigateTo: () -> Unit,
    publishFunny: (Funny) -> Unit,
    voteOnFunny: (Funny) -> Unit,
    isFromRecorder: Boolean = true
) {
    var showDialog by remember { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxHeight().background(Color.Black)
    ) {
        PlayerPreview(
            player = player,
            onCancelClick = {
                onCancel(funny)
                onCancelNavigateTo()
            }
        )

        Spacer(Modifier.height(55.dp))

        PlayerController(
            isFromRecorder = isFromRecorder,
            onLike = {
                funny.likes.inc()
                voteOnFunny(funny)
                showDialog = true
            },
            onDisLike = {
                funny.likes.dec()
                voteOnFunny(funny)
                showDialog = true
            },
            onSend = {
                publishFunny(funny)
                onCancelNavigateTo()
            }
        )

        if (showDialog) {
            OnVoteDialog(
                onConfirm = onCancelNavigateTo,
                onRefuse = onCancelNavigateTo
            ) { }
        }
    }
}