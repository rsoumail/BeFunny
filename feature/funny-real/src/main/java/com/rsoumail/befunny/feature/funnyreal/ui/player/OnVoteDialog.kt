package com.rsoumail.befunny.feature.funnyreal.ui.player

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rsoumail.befunny.feature.funnyreal.R

@Composable
fun OnVoteDialog(
    onConfirm: () -> Unit,
    onRefuse: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        title = { Text(stringResource(R.string.on_vote_dialog_title)) },
        text = { Text(stringResource(R.string.on_vote_dialog_description)) },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                },
                modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonColors(
                    contentColor = Color.White,
                    containerColor = Color(red = 17, green = 155, blue = 21),
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.Gray,
                )
            ) {
                Text(
                    text = stringResource(R.string.yes),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
                )
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = {
                    onRefuse()
                },
                modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp),
                border = BorderStroke(1.dp, Color.Red),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
            ) {
                Text(
                   text =  stringResource(R.string.no),
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
                    fontSize = 12.sp,
                )
            }
        }
    )
}
