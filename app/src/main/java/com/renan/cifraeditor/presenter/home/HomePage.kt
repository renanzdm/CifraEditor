package com.renan.cifraeditor.presenter.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.renan.cifraeditor.presenter.ui.AppRoutes
import com.renan.cifraeditor.presenter.ui.components.AppTopBar

@Composable
fun HomePage(navController: NavController) {
    val scrollState = rememberScrollState()
    Scaffold(topBar = { AppTopBar(title = "Bem Vindo")}) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
        Button(onClick = { navController.navigate(AppRoutes.soundControlRoute) }) {
            Text("Controlar Som")
        }

        }
    }
}