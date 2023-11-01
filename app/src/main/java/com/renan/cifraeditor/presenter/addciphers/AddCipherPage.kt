package com.renan.cifraeditor.presenter.addciphers

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.renan.cifraeditor.R
import com.renan.cifraeditor.domain.entities.TomEntity
import com.renan.cifraeditor.presenter.ui.components.AppTopBar
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddCipherPage(
    navController: NavController,
    addCipherViewModel: AddCipherViewModel = hiltViewModel(),
) {
    val pagerState = rememberPagerState(pageCount = { 3 }, initialPage = 0)
    val uiState = addCipherViewModel.state.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    LifecycleStartEffect(lifecycleOwner = LocalLifecycleOwner.current) {
        addCipherViewModel.getAllToms()
        onStopOrDispose { }
    }

    Scaffold(topBar = {
        AppTopBar(title = "Nova Cifra")
    }) { padding ->
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 104.dp)
                .statusBarsPadding()
                .navigationBarsPadding()
                .imePadding()
        ) {
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
                verticalAlignment = Alignment.Top,
            ) { page ->
                when (page) {
                    0 -> AddLetterCipherPage()
                    1 -> AddTom(toms = uiState.value.allToms)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
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
                ElevatedButton(onClick = { /*TODO*/ }) {
                    Text(text = "Voltar")
                }
                Spacer(modifier = Modifier.weight(1f))
                ElevatedButton(onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage + 1
                        )
                    }
                }) {
                    Text(text = "Avançar")
                }
            }

        }
    }
}


@Composable
fun AddLetterCipherPage() {
    var artistName by remember { mutableStateOf("") }
    var musicName by remember { mutableStateOf("") }
    Column {
        OutlinedTextField(
            value = artistName,
            label = { Text("Artista") },
            onValueChange = { value -> artistName = value },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Face, contentDescription = "Nome Artista"
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            label = { Text("Música") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_music_note_24),
                    contentDescription = "Nome Artista"
                )
            },
            value = musicName,
            onValueChange = { value -> musicName = value },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTom(toms: List<TomEntity>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedValue: TomEntity? by remember { mutableStateOf(null) }
    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
        expanded = !expanded
    }) {
        Row(
            modifier = Modifier
                .menuAnchor()
                .width(120.dp)
        ) {
            Text(
                selectedValue?.name ?: "",
            )
            Icon(
                imageVector = Icons.Outlined.ArrowDropDown, contentDescription = "Open DropDown"
            )
        }

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            toms.forEach { item ->
                DropdownMenuItem(text = { Text(text = item.name) }, onClick = {
                    selectedValue = item
                    expanded = false
                })
            }
        }
    }
}

