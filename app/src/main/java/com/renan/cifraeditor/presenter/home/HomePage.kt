package com.renan.cifraeditor.presenter.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Suas Cifras",
                style = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 28.sp),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            AnimatedVisibility(
                visible = uiState.value.ciphers.isEmpty(), content = {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    textAlign = TextAlign.Center,
                    text = "Ainda não há nenhuma cifra 🎼",
                    style = TextStyle(fontWeight = FontWeight.Light)
                )
            })
            AnimatedVisibility(
                visible = uiState.value.loading,
                content = { CircularProgressIndicator() })
            AnimatedVisibility(visible = !uiState.value.loading) {
                LazyColumn {
                    items(items = uiState.value.ciphers) { item ->
                        ListItem(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable {
                                    val route: String = AppRoutes.replaceParam(
                                        route = AppRoutes.cipherDetailsRoute,
                                        nameParam = "id",
                                        value = item.cipherId!!
                                    )
                                    navController.navigate(route)
                                },
                            overlineContent = {
                                Text(
                                    text = item.cipherName, style = TextStyle(
                                        fontWeight = FontWeight.ExtraBold, fontSize = 16.sp
                                    )
                                )
                            },
                            headlineContent = {
                                Text(
                                    text = item.cipherArtist ?: "",
                                    style = TextStyle(fontWeight = FontWeight.Light)
                                )
                            },
                            tonalElevation = 12.dp,
                        )
                    }
                }
            }
        }

    }


}