package com.renan.cifraeditor.presenter.cipherdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.renan.cifraeditor.domain.entities.entitiesrelations.WordWithChords


@OptIn(ExperimentalLayoutApi::class)
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
                text = uiState.value.name ?: "", style = TextStyle(
                    color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold
                )
            )
            Text(text = uiState.value.artist ?: "", style = TextStyle(color = Color.White))
            LazyColumn {
                items(items = uiState.value.wordsFormatted) { items ->
                    FlowRow {
                        items.map { WordCard(entity = it) }
                    }
                }

            }

        }
    }
}



@Composable
fun WordCard(entity: WordWithChords) {
    Column(modifier = Modifier.clickable {
        println("PRINTOU")
    }) {
       LazyRow{
           items(items = entity.chords){
               Text(
                   text = it!!.chordName,
                   style = TextStyle(color = Color.Yellow),
                   modifier = Modifier.padding(horizontal = 4.dp)
               )
           }
       }
        Text(
            text = entity.word.wordName, modifier = Modifier.padding(horizontal = 1.dp)
        )
    }


}