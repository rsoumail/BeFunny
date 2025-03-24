package com.rsoumail.befunny.feature.funnyreal.ui.recorder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rsoumail.befunny.feature.funnyreal.R

@Composable
fun RecorderController(
    onRecordClick: () -> Unit,
    onSwitchCameraClick: () -> Unit,
    onGalleryClick: () -> Unit,
    isVideoRecording: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        IconButton(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .size(60.dp)
                .background(Color.Transparent),

            onClick = {
                onGalleryClick()
            }
        ) {
            Icon(
                imageVector = Icons.Default.PhotoLibrary,
                contentDescription = stringResource(R.string.open_funnies_description),
                modifier = Modifier
                    .size(35.dp),
                tint = Color.White
            )
        }

        IconButton(
            onClick = {
                onRecordClick()
            },
            modifier = Modifier
                .size(70.dp)
                .background(Color.Transparent),
        ) {
            Icon(
                imageVector = if (!isVideoRecording) {
                    Icons.Default.Videocam
                } else {
                    Icons.Default.StopCircle
                },
                contentDescription = if (!isVideoRecording) {
                    stringResource(R.string.record_video_description)
                } else {
                    stringResource(R.string.stop_description)
                },
                modifier = Modifier.size(40.dp),
                tint = Color.White
            )
        }

        IconButton(
            onClick = {
                onSwitchCameraClick()
            },
            modifier = Modifier
                .size(60.dp)
                .background(Color.Transparent),
        ) {
            Icon(
                imageVector = Icons.Default.Cameraswitch,
                contentDescription = stringResource(R.string.switch_camera_description),
                modifier = Modifier.size(35.dp),
                tint = Color.White
            )
        }
    }
}