package com.rsoumail.befunny.feature.funnyreal.ui.recorder

import android.annotation.SuppressLint
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rsoumail.befunny.feature.funnyreal.ui.common.model.Funny
import kotlinx.coroutines.launch

@SuppressLint("MissingPermission")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecorderScreen(
    funnies: List<Funny>,
    record: () -> Unit,
    switchCamera: () -> Unit,
    isVideoRecording: Boolean,
    loadFunnies: () -> Unit,
    remainingTime: String,
    controller: LifecycleCameraController
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        loadFunnies()
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            FunniesGallery(funnies)
        }
    ) { padding ->
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.Black)
                .padding(padding)
        ) {
            Box(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    remainingTime,
                    modifier = Modifier,
                    color = Color.White,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(15.dp))

            CameraPreview(controller)

            Spacer(Modifier.height(25.dp))

            RecorderController(
                onRecordClick = { record() },
                onSwitchCameraClick = { switchCamera() },
                onGalleryClick = {
                    scope.launch {
                        scaffoldState.bottomSheetState.expand()
                    }
                },
                isVideoRecording = isVideoRecording
            )
        }
    }
}