package com.renan.cifraeditor.presenter.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavController
import com.renan.cifraeditor.presenter.ui.AppRoutes

@Composable
fun HomePage(homeViewModel: HomePageViewModel = hiltViewModel(), navController: NavController) {
    val uiState = homeViewModel.state.collectAsStateWithLifecycle()
    var isSearch by remember { mutableStateOf(false) }
    var searchValue by remember { mutableStateOf("") }

    LifecycleStartEffect(lifecycleOwner = LocalLifecycleOwner.current) {
        homeViewModel.initDataInitialDatabase()
        homeViewModel.getCiphers()
        onStopOrDispose { }
    }
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            navController.navigate(AppRoutes.addCipherRoute)
        }) {
            Icon(imageVector = Icons.Rounded.Add, contentDescription = "Adicionar")
        }
    }) {
        AnimatedVisibility(visible = uiState.value.loading, content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        })

        AnimatedVisibility(visible = !uiState.value.loading) {

            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AnimatedVisibility(visible = isSearch) {
                        OutlinedTextField(trailingIcon = {
                            IconButton(onClick = {
                                isSearch = false
                                searchValue = ""
                                homeViewModel.searchCipher(searchValue)
                            }) {
                                Icon(
                                    imageVector = Icons.Outlined.Close,
                                    contentDescription = "fechar busca",
                                    modifier = Modifier
                                        .height(30.dp)
                                        .width(30.dp)
                                )
                            }
                        }, label = {
                            Text(
                                "Nome da música, ou artista",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light
                            )
                        }, value = searchValue, onValueChange = { value ->
                            searchValue = value
                            homeViewModel.searchCipher(searchValue)

                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)

                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { isSearch = true }) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "buscar cifras",
                            modifier = Modifier
                                .height(30.dp)
                                .width(30.dp)
                        )
                    }
                }

                Text(
                    text = "Cifras",
                    style = TextStyle(),
                    fontSize = 48.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(16.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))
                AnimatedVisibility(visible = uiState.value.ciphers.isEmpty(), content = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Nenhuma cifra encontrada...",
                        fontSize = 16.sp,
                        style = TextStyle(fontWeight = FontWeight.Light)
                    )
                })

                LazyColumn {
                    items(items = uiState.value.ciphers) { item ->
                        Row(modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable {
                                val route: String = AppRoutes.replaceParam(
                                    route = AppRoutes.cipherDetailsRoute,
                                    nameParam = "id",
                                    value = item.cipherId!!
                                )
                                navController.navigate(route)
                            }, content = {
                            Column(
                                modifier = Modifier.padding(
                                    horizontal = 20.dp,
                                    vertical = 12.dp
                                )
                            ) {
                                Text(
                                    text = item.cipherName, style = TextStyle(
                                        fontWeight = FontWeight.ExtraBold, fontSize = 22.sp
                                    )
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = item.cipherArtist ?: "",
                                    style = TextStyle(fontWeight = FontWeight.Normal),
                                    color = Color.Gray
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                HorizontalDivider()
                            }
                        }

                        )
                    }
                }

            }
        }
    }


}