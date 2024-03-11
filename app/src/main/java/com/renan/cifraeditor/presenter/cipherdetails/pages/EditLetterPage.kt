package com.renan.cifraeditor.presenter.cipherdetails.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.renan.cifraeditor.presenter.cipherdetails.CipherDetailsViewModel
import com.renan.cifraeditor.presenter.ui.components.CustomTopAppBar

@Composable
fun EditLetterPage(cipherDetailsViewModel: CipherDetailsViewModel, navController: NavController) {
    var letterMusic by remember { mutableStateOf(cipherDetailsViewModel.joinWordsToString()) }
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = { CustomTopAppBar(backOnTap = { navController.popBackStack() }) }
    ) { pdv ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .imePadding()
                .padding(horizontal = 16.dp)
                .padding(pdv)
                .verticalScroll(state = scrollState)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = letterMusic,
                onValueChange = {
                    letterMusic = it
                },
                label = { Text("Letra da Música") },
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = {
                cipherDetailsViewModel.editCipher(letterMusic)
                navController.popBackStack()
            }) {
                Text(text = "Editar")
            }

        }
    }
}