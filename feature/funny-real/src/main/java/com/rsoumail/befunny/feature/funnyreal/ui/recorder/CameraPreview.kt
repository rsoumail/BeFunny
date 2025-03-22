package com.rsoumail.befunny.feature.funnyreal.ui.recorder

import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun CameraPreview(
    controller: LifecycleCameraController
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .height(550.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
        ) {
            AndroidView(
                factory = { context ->
                    PreviewView(context).apply {
                        this.controller = controller
                        controller.bindToLifecycle(lifecycleOwner)
                    }
                },
                onRelease = {
                    controller.unbind()
                },
                modifier = Modifier.matchParentSize()
                    .align(Alignment.Center)
            )
        }
    }
}