package com.rsoumail.befunny.feature.funnyreal.ui.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rsoumail.befunny.feature.funnyreal.R

@Composable
fun PlayerController(
    isFromRecorder: Boolean = false,
    onLike: () -> Unit,
    onDisLike: () -> Unit,
    onSend: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        if (isFromRecorder) {
            IconButton(
                modifier = Modifier
                    .background(Color.Transparent),
                onClick = {
                    onSend()
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
        } else {

            IconButton(
                modifier = Modifier
                    .background(Color.Transparent),
                onClick = {
                    onDisLike()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ThumbDown,
                    contentDescription = stringResource(R.string.send_description),
                    modifier = Modifier
                        .size(45.dp),
                    tint = Color.White
                )
            }

            IconButton(
                modifier = Modifier
                    .background(Color.Transparent),
                onClick = {
                    onLike()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ThumbUp,
                    contentDescription = stringResource(R.string.send_description),
                    modifier = Modifier
                        .size(45.dp),
                    tint = Color.White
                )
            }
        }
    }
}