package com.rsoumail.befunny.feature.funnyreal.ui.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.rsoumail.befunny.feature.funnyreal.R

@Composable
fun PlayerPreview(
    player: ExoPlayer,
    onCancelClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(Color.Black)
            .height(400.dp)
            .fillMaxWidth()
            .clipToBounds()
            .padding(20.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    useController = false
                    this.player = player
                }
            },
            onRelease = {
                // player.release()
            }
        )

        IconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .background(Color.Transparent),
            onClick = {
                onCancelClick()
            }
        ) {
            Icon(
                imageVector = Icons.Default.Cancel,
                contentDescription = stringResource(R.string.send_description),
                modifier = Modifier
                    .size(35.dp),
                tint = Color.White
            )
        }
    }
}