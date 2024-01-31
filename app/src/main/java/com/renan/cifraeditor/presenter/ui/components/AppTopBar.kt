package com.renan.cifraeditor.presenter.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    backOnTap: (() -> Unit)? = null,
    title: String? = null,
    icon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},

    ) {
    TopAppBar(modifier = Modifier.fillMaxWidth(), actions = actions, title = {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.invoke()
            if (title != null) Text(text = title, fontWeight = FontWeight.Bold)
        }
    }, navigationIcon = {
        AnimatedVisibility(visible = backOnTap != null) {
            IconButton(onClick = { backOnTap?.invoke() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Voltar",

                    )
            }
        }
    })
}