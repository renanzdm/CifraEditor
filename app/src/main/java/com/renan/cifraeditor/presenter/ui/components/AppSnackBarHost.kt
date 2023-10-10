package com.renan.cifraeditor.presenter.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@Composable
fun AppSnackBarHost(
    hostState: SnackbarHostState,
    color: Color = Color(0x70161515)
) {
    SnackbarHost(
        hostState = hostState,
        snackbar = { data: SnackbarData ->
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .wrapContentSize()
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color)
                        .padding(22.dp),
                    text = data.visuals.message,
                    color = color, style = TextStyle(
                        color = Color.White
                    )
                )
            }
        }
    )

}