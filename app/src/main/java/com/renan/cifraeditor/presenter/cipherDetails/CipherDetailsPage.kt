package com.renan.cifraeditor.presenter.cipherDetails

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.renan.cifraeditor.domain.entities.WordsEntity
import com.renan.cifraeditor.presenter.ui.components.AppTopBar

@Composable

fun CipherDetailsPage(
    navController: NavController,
    cipherDetailsViewModel: CipherDetailsViewModel = hiltViewModel(),
    idCipher: Long?
) {
    val uiState = cipherDetailsViewModel.state.collectAsStateWithLifecycle()
    LifecycleStartEffect(lifecycleOwner = LocalLifecycleOwner.current) {
        if (idCipher != null) cipherDetailsViewModel.getCipherById(idCipher)
        onStopOrDispose { }
    }
    Scaffold { pdv ->
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(pdv)
        ) {
            Box(modifier = Modifier.height(40.dp))
            Text(
                text = uiState.value.name ?: "",
                style = TextStyle(
                    color = Color.White, fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = uiState.value.artist ?: "",
                style = TextStyle(color = Color.White)
            )
            uiState.value.words.map { wordsEntity -> WordCard(entity = wordsEntity) }

        }
    }
}


@Composable
fun WordCard(entity: WordsEntity) {
    Column(modifier = Modifier.clickable {
        println("PRINTOU")
    }) {
        Text(text = "Acorde")
        Text(text = entity.name)
    }


}