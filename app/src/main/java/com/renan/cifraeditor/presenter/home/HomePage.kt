package com.renan.cifraeditor.presenter.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.renan.cifraeditor.presenter.ui.AppRoutes
import com.renan.cifraeditor.presenter.ui.components.AppTopBar

@Composable
fun HomePage(homeViewModel: HomePageViewModel = hiltViewModel(), navController: NavController) {
    val uiState = homeViewModel.state.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()
    LifecycleStartEffect(lifecycleOwner = LocalLifecycleOwner.current) {
        homeViewModel.getCiphers()
        onStopOrDispose { }
    }
    Scaffold( floatingActionButton = {
        FloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Rounded.Add, contentDescription = "Adicionar")
        }
    }) {
        Column(
            modifier = Modifier

                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visible = uiState.value.loading
            ) {
                CircularProgressIndicator()
            }
            AnimatedVisibility(visible = !uiState.value.loading) {
                LazyColumn {
                    items(items = uiState.value.ciphers) { item ->
                        ListItem(modifier = Modifier.clickable {
                            val route: String = AppRoutes.replaceParam(
                                route = AppRoutes.cipherDetailsRoute,
                                nameParam = "id",
                                value = item.id!!
                            )
                            navController.navigate(route)
                        }, headlineContent = { Text(text = item.name) })
                    }
                }
            }
        }

    }


}