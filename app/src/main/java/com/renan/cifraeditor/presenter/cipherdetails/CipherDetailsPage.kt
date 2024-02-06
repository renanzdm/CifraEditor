package com.renan.cifraeditor.presenter.cipherdetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.renan.cifraeditor.domain.database.allToms
import com.renan.cifraeditor.domain.entities.entitiesrelations.WordWithChords
import com.renan.cifraeditor.domain.entities.tables.Chord
import com.renan.cifraeditor.domain.entities.tables.Tom
import com.renan.cifraeditor.presenter.ui.AppRoutes
import com.renan.cifraeditor.presenter.ui.components.CustomTopAppBar
import com.renan.cifraeditor.presenter.ui.components.ModalConfirmButton
import com.renan.cifraeditor.presenter.ui.theme.md_theme_dark_onSecondary
import com.renan.cifraeditor.presenter.ui.theme.md_theme_dark_outlineVariant
import kotlinx.coroutines.launch


@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable

fun CipherDetailsPage(
    cipherDetailsViewModel: CipherDetailsViewModel,
    idCipher: Long?,
    navController: NavController
) {
    val uiState = cipherDetailsViewModel.state.collectAsStateWithLifecycle()
    var showPopUpButton by remember { mutableStateOf(false) }
    var showModalConfirmDelete by remember { mutableStateOf(false) }
    var showModalConfirmEdit by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    LifecycleStartEffect(lifecycleOwner = LocalLifecycleOwner.current) {
        if (idCipher != null) {
            cipherDetailsViewModel.getCipherById(idCipher)
        }
        onStopOrDispose { }
    }


    if (showModalConfirmDelete) {
        ModalConfirmButton(setShowDialog = { showModalConfirmDelete = false },
            text = "Deseja excluir?",
            buttons = listOf {
                Button(onClick = {
                    showPopUpButton = false
                    cipherDetailsViewModel.deleteCipher()
                    navController.popBackStack()
                }) {
                    Text(text = "Sim")
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(onClick = { showModalConfirmDelete = false }) {
                    Text(text = "Não")
                }
            })
    }

    if (showModalConfirmEdit) {
        ModalConfirmButton(setShowDialog = { showModalConfirmEdit = false },
            text = "Deseja editar a letra?",
            buttons = listOf {
                Button(onClick = {
                    showModalConfirmEdit = false
                    navController.navigate(AppRoutes.editLetterPage)
                }) {
                    Text(text = "Sim")
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(onClick = { showModalConfirmEdit = false }) {
                    Text(text = "Não")
                }
            })
    }

    if (showPopUpButton) {
        Popup(
            onDismissRequest = { showPopUpButton = false }, alignment = Alignment.TopEnd
        ) {
            Box(modifier = Modifier.padding(horizontal = 12.dp, vertical = 40.dp)) {
                Column(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(10)
                        )
                        .background(color = md_theme_dark_onSecondary)
                ) {
                    TextButton(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        onClick = {
                            showModalConfirmDelete = true
                        }) {
                        Text(text = "Excluir")
                    }
                    TextButton(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        onClick = {
                            showModalConfirmEdit = true
                        }) {
                        Text(text = "Editar Letra")
                    }
                }
            }
        }

    }

    Scaffold(topBar = {
        CustomTopAppBar(backOnTap = { navController.popBackStack() }, actions = {
            IconButton(onClick = {
                showPopUpButton = true
            }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "options")
            }
        })
    }) { pdv ->
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(pdv)
        ) {

            if (uiState.value.loading) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            } else {

                Text(
                    text = uiState.value.cipher?.cipherName ?: "", style = TextStyle(
                        color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = uiState.value.cipher?.cipherArtist ?: "",
                    style = TextStyle(color = Color.White)
                )
                Spacer(modifier = Modifier.height(20.dp))
                ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
                    expanded = !expanded
                }) {
                    Row(
                        modifier = Modifier
                            .width(80.dp)
                            .menuAnchor()
                    ) {
                        Text(
                            uiState.value.tomOfCipher?.tomName ?: "",
                        )
                        Spacer(modifier = Modifier.width(20.dp))

                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }) {
                        uiState.value.listToms.forEach { item ->
                            DropdownMenuItem(text = { Text(text = item.tomName) }, onClick = {
                                expanded = false
                                cipherDetailsViewModel.selectNewTom(item)
                            })
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                AnimatedVisibility(visible = uiState.value.wordsFormatted.isNotEmpty()) {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(items = uiState.value.wordsFormatted, key = { item ->
                            item.hashCode()
                        }) { items ->
                            FlowRow {
                                items.map { wordWithChords ->
                                    WordCard(entity = wordWithChords,
                                        chords = uiState.value.chords,
                                        deleteChord = { chordSelected ->
                                            cipherDetailsViewModel.deleteWordChordCrossReference(
                                                word = wordWithChords.word, chord = chordSelected
                                            )
                                            cipherDetailsViewModel.getCipherById(
                                                cipherDetailsViewModel.state.value.cipher!!.cipherId!!
                                            )

                                        },
                                        selectValue = { chordSelected ->
                                            cipherDetailsViewModel.insertWordChordCrossReference(
                                                word = wordWithChords.word, chord = chordSelected
                                            )
                                        })
                                }
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
fun WordCard(
    entity: WordWithChords,
    chords: List<Chord>,
    selectValue: (Chord) -> Unit,
    deleteChord: (Chord) -> Unit
) {
    val showDialogAddChord = remember { mutableStateOf(false) }
    val showDialogRemoveChord = remember { mutableStateOf(false) }
    if (showDialogAddChord.value) {
        DialogAddChord(
            setShowDialog = { showDialogAddChord.value = it },
            chords = chords,
            selectValue = selectValue,
        )
    }
    if (showDialogRemoveChord.value) {
        DialogRemoveChord(
            setShowDialog = { showDialogRemoveChord.value = it },
            deleteChord = deleteChord,
            entity = entity
        )
    }
    Column(verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = { _ ->
                    if (!entity.chords.isNullOrEmpty()) showDialogRemoveChord.value = true
                }, onTap = { _ -> showDialogAddChord.value = true })
            }) {
        if (!entity.chords.isNullOrEmpty()) {
            LazyRow {
                items(
                    key = { item -> item.hashCode() },
                    items = entity.chords
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .height(14.dp),
                        text = it.chordName,
                        textAlign = TextAlign.Center,
                        style = TextStyle(color = Color.Yellow, fontSize = 12.sp),
                    )
                }
            }
        } else {
            Spacer(modifier = Modifier.height(14.dp))
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
    var otherTom by remember { mutableStateOf(false) }
    var listNewChords by remember { mutableStateOf<List<Chord>?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val cipherDetailsViewModel: CipherDetailsViewModel = hiltViewModel()


    BasicAlertDialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            modifier = Modifier.padding(8.dp),
            shape = RoundedCornerShape(8),
            shadowElevation = 12.dp,
            color = md_theme_dark_outlineVariant
        ) {
            if (otherTom) {
                if (listNewChords != null) {
                    LazyColumn(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        item {
                            IconButton(onClick = { listNewChords = null }, content = {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Voltar"
                                )
                            })
                        }
                        items(items = listNewChords ?: emptyList(), itemContent = { item: Chord ->
                            Column(modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .clickable {
                                    setShowDialog(false)
                                    selectValue.invoke(item)

                                }) {
                                Text(
                                    text = item.chordName, style = TextStyle(
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Grau: " + item.chordDegree.toString(),
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Thin
                                    )
                                )
                            }
                        })


                    }

                } else {
                    Column {

                        IconButton(onClick = { otherTom = false }, content = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Voltar"
                            )
                        })
                        LazyColumn(
                            modifier = Modifier.padding(8.dp),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.Start
                        ) {

                            items(items = allToms, itemContent = { item: Tom ->
                                Text(text = item.tomName,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth()
                                        .clickable {
                                            coroutineScope.launch {
                                                listNewChords =
                                                    cipherDetailsViewModel.getChordsByTomId(item.tomId!!)
                                            }
                                        })
                            })


                        }
                    }
                }

            } else {
                LazyColumn(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    item {
                        Text(
                            text = "Adicionar Novos Acordes",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        )
                    }
                    items(
                        items = chords
                    ) { chord ->
                        Column(modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .clickable {
                                setShowDialog(false)
                                selectValue.invoke(chord)
                            }) {
                            Text(
                                text = chord.chordName, style = TextStyle(
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Grau: " + chord.chordDegree.toString(), style = TextStyle(
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Thin
                                )
                            )
                        }
                    }
                    item {
                        Button(modifier = Modifier.fillMaxWidth(), onClick = {
                            otherTom = true
                        }) {
                            Text(text = "Adicionar acorde de outro tom")
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                        }
                    }
                }
            }

        }

    }
}

@ExperimentalMaterial3Api
@Composable
fun DialogRemoveChord(
    setShowDialog: (Boolean) -> Unit, deleteChord: (Chord) -> Unit, entity: WordWithChords
) {
    BasicAlertDialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            modifier = Modifier.padding(8.dp),
            shape = RoundedCornerShape(8),
            shadowElevation = 12.dp,
            color = md_theme_dark_outlineVariant
        ) {
            LazyColumn(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                item {
                    Row {
                        Text(
                            text = "Remover Acordes",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(onClick = { setShowDialog(false) }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                    }
                }
                if (!entity.chords.isNullOrEmpty()) {
                    items(items = entity.chords) { chord ->
                        Row {
                            Text(
                                modifier = Modifier.padding(16.dp), text = chord.chordName
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(onClick = { deleteChord(chord) }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }

            }
        }

    }
}

