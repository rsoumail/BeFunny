package com.rsoumail.befunny.feature.funnyreal.ui.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.rsoumail.befunny.feature.funnyreal.R
import com.rsoumail.befunny.feature.funnyreal.ui.common.model.Funny

@Composable
fun FunnyPlayerScreen(
    funny: Funny,
    player: ExoPlayer,
    onCancel: (Funny) -> Unit,
    onCancelNavigateTo: () -> Unit,
    publishFunny: (Funny) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .height(550.dp)
                .clip(RoundedCornerShape(15.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                player.apply {
                    setMediaItem(
                        MediaItem.fromUri(funny.location)
                    )
                    prepare()
                    playWhenReady = true
                }

                PlayerPreview(
                    player = player,
                    onCancelClick = {
                        onCancel(funny)
                        onCancelNavigateTo()
                    }
                )

            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    modifier = Modifier
                        .background(Color.Transparent),
                    onClick = {
                        publishFunny(funny)
                        onCancelNavigateTo()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = stringResource(R.string.send_description),
                        modifier = Modifier
                            .size(45.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }
}