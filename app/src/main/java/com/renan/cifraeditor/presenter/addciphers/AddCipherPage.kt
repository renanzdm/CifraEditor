package com.renan.cifraeditor.presenter.addciphers

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.compose.md_theme_dark_onError
import com.renan.cifraeditor.R
import com.renan.cifraeditor.domain.database.allToms
import com.renan.cifraeditor.domain.entities.tables.Tom
import com.renan.cifraeditor.presenter.ui.AppRoutes
import com.renan.cifraeditor.presenter.ui.components.AppSnackBarHost
import com.renan.cifraeditor.presenter.ui.components.CustomTopAppBar
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddCipherPage(
    addCipherViewModel: AddCipherViewModel = hiltViewModel(), navController: NavController
) {
    val snackHost = remember { SnackbarHostState() }
    val pagerState = rememberPagerState(pageCount = { 3 }, initialPage = 0)
    val uiState = addCipherViewModel.state.collectAsStateWithLifecycle()
    var isError by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = uiState.value.errorMessage, key2 = uiState.value.idCipherCreated) {
        if (uiState.value.errorMessage?.isNotEmpty() == true) {
            snackHost.showSnackbar(
                message = uiState.value.errorMessage ?: ""
            )
        }
        if (uiState.value.idCipherCreated != null) {
            val route: String = AppRoutes.replaceParam(
                route = AppRoutes.cipherDetailsRoute,
                nameParam = "id",
                value = uiState.value.idCipherCreated
            )
            navController.navigate(route) {
                popUpTo(AppRoutes.homeRoute)
            }
        }
    }

    Scaffold(snackbarHost = { AppSnackBarHost(hostState = snackHost) }, topBar = {
        CustomTopAppBar(title = "Nova Cifra", backOnTap = {navController.popBackStack()})
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .imePadding()
        ) {
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
                verticalAlignment = Alignment.Top,
            ) { page ->
                when (page) {
                    0 -> AddNameCipherPage(isError = isError)
                    1 -> AddTomPage()
                    2 -> AddLetterPage()
                }
            }
            Spacer(
                modifier = Modifier
                    .navigationBarsPadding()
                    .weight(1f),
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(3) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(10.dp)
                    )
                }
            }
            Row {
                ElevatedButton(onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage - 1
                        )
                    }
                }) {
                    Text(text = "Voltar")
                }
                Spacer(modifier = Modifier.weight(1f))
                if (pagerState.currentPage == 2) {
                    ElevatedButton(onClick = {
                        coroutineScope.launch { addCipherViewModel.saveCipher() }
                    }, content = { Text(text = "Salvar") })
                } else {
                    ElevatedButton(onClick = {
                        isError = addCipherViewModel.validateNameMusic()
                        coroutineScope.launch {
                            if (!isError) pagerState.animateScrollToPage(
                                pagerState.currentPage + 1
                            )
                        }
                    }, content = { Text(text = "Avançar") })
                }
            }

        }
    }
}


@Composable
fun AddNameCipherPage(isError: Boolean) {
    var artistName by remember { mutableStateOf("") }
    var musicName by remember { mutableStateOf("") }
    val addCipherViewModel = hiltViewModel<AddCipherViewModel>()
    Column(
    ) {
        OutlinedTextField(
            label = { Text(if (isError) "Nome da Música *" else "Nome da Música") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_music_note_24),
                    contentDescription = "Nome da Música"
                )
            },
            isError = isError,
            value = musicName,
            onValueChange = { value ->
                musicName = value
                addCipherViewModel.setNameMusic(value)
            },
            supportingText = {
                if (isError) Text(
                    modifier = Modifier.fillMaxWidth(),
                    color = md_theme_dark_onError,
                    text = "Nome da música é obrigatório",
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                errorBorderColor = md_theme_dark_onError,
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = artistName,
            label = { Text("Nome do Artista") },
            onValueChange = { value ->
                artistName = value
                addCipherViewModel.setNameArtist(value)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Face, contentDescription = "Nome do Artista"
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTomPage() {
    var expanded by remember { mutableStateOf(false) }
    val addCipherViewModel = hiltViewModel<AddCipherViewModel>()
    var selectedValue: Tom by remember { mutableStateOf(allToms.first()) }
    LifecycleStartEffect(key1 = true, lifecycleOwner = LocalLifecycleOwner.current) {
        addCipherViewModel.setTom(selectedValue.tomId!!)
        onStopOrDispose { }
    }


    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
        expanded = !expanded
    }) {
        TextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            value = selectedValue.tomName,
            onValueChange = {},
            label = { Text("Tom") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            allToms.forEach { item ->
                DropdownMenuItem(text = { Text(text = item.tomName) }, onClick = {
                    selectedValue = item
                    expanded = false
                    addCipherViewModel.setTom(item.tomId!!)
                })
            }
        }
    }
}

@Composable
fun AddLetterPage() {
    val localConfiguration = LocalConfiguration.current
    var letterMusic by remember { mutableStateOf("") }
    val addCipherViewModel = hiltViewModel<AddCipherViewModel>()
    Column(modifier = Modifier.imePadding()) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = (localConfiguration.screenHeightDp * 0.68).dp),
            value = letterMusic,
            onValueChange = {
                letterMusic = it
                addCipherViewModel.setLetterMusic(letterMusic)
            },
            label = { Text("Letra da Música") },
        )
    }
}


