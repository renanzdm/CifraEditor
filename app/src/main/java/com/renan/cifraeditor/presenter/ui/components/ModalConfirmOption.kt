package com.renan.cifraeditor.presenter.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.renan.cifraeditor.presenter.ui.theme.md_theme_dark_outlineVariant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalConfirmButton(
    setShowDialog: (Boolean) -> Unit,
    text: String,
    buttons: List<@Composable () -> Unit>
) {
    BasicAlertDialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            modifier = Modifier.padding(8.dp),
            shape = RoundedCornerShape(8),
            shadowElevation = 12.dp,
            color = md_theme_dark_outlineVariant

        ) {
            Column (modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                Text(text = text)
                Spacer(modifier = Modifier.height(12.dp))
                Row {
                    buttons.forEach { it.invoke() }
                }
            }


        }


    }


}