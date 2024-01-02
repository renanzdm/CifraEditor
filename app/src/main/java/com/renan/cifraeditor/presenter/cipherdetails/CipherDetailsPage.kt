package com.renan.cifraeditor.presenter.cipherdetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.md_theme_dark_outlineVariant
import com.renan.cifraeditor.domain.entities.entitiesrelations.WordWithChords
import com.renan.cifraeditor.domain.entities.tables.Chord


@OptIn(ExperimentalLayoutApi::class)
@Composable

fun CipherDetailsPage(
    cipherDetailsViewModel: CipherDetailsViewModel = hiltViewModel(), idCipher: Long?
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
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = uiState.value.name ?: "", style = TextStyle(
                    color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold
                )
            )
            Text(text = uiState.value.artist ?: "", style = TextStyle(color = Color.White))
            Spacer(modifier = Modifier.height(20.dp))
            AnimatedVisibility(visible = uiState.value.wordsFormatted != null) {
                LazyColumn {
                    items(items = uiState.value.wordsFormatted!!, key = { item ->
                        item.hashCode()
                    }) { items ->
                        FlowRow {
                            items.map {
                                WordCard(
                                    entity = it, chords = uiState.value.chords ?: emptyList()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordCard(entity: WordWithChords, chords: List<Chord>) {
    val showDialog = remember { mutableStateOf(false) }
    val cipherDetailsViewModel: CipherDetailsViewModel = hiltViewModel()
    if (showDialog.value) {
        DialogAddChord(
            setShowDialog = { showDialog.value = it },
            chords = chords,
            selectValue = {
                cipherDetailsViewModel.updateWordChordCrossReference(word = entity.word, chord = it)
                cipherDetailsViewModel.getCipherById(cipherDetailsViewModel.state.value.idCipher!!)
            },
        )
    }

    Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier
        .height(40.dp)
        .clickable {
            showDialog.value = true
        }) {

        if (entity.chords != null) LazyRow {
            items(items = entity.chords, key = {
                it.chordName
            }) {
                Text(
                    text = it.chordName,
                    textAlign = TextAlign.Center,
                    style = TextStyle(color = Color.Yellow, fontSize = 12.sp),
                )
            }
        }
        Text(
            text = entity.word.wordName,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 1.dp)
        )
    }
}


@ExperimentalMaterial3Api
@Composable
fun DialogAddChord(
    setShowDialog: (Boolean) -> Unit, selectValue: (Chord) -> Unit, chords: List<Chord>
) {
    BasicAlertDialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            modifier = Modifier.padding(8.dp),
            shape = RoundedCornerShape(8),
            shadowElevation = 12.dp,
            color = md_theme_dark_outlineVariant
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                items(
                    items = chords
                ) {
                    Column(modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            setShowDialog(false)
                            selectValue.invoke(it)
                        }) {
                        Text(
                            text = it.chordName, style = TextStyle(
                                color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Grau: " + it.chordDegree.toString(), style = TextStyle(
                                color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Thin
                            )
                        )
                    }
                }
            }

        }

    }
}