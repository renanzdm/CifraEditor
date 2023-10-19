package com.renan.cifraeditor.presenter.soundcontrol

import android.Manifest
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.renan.cifraeditor.presenter.ui.components.AppTopBar

@Composable
fun SoundControlPage(
    navController: NavController, soundControlViewModel: SoundControlViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val uiState = soundControlViewModel.state.collectAsStateWithLifecycle()
    val permissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) {
            soundControlViewModel.startScan()
        }
    val listPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
    )
    if (uiState.value.openDialog) {
        DialogAvailableNetworks(
            closeDialog = { soundControlViewModel.closeDialogResults() },
            listResults = uiState.value.availableNetwork,
            loading = uiState.value.loading
        )
    }
    LifecycleStartEffect(lifecycleOwner = LocalLifecycleOwner.current) {
        soundControlViewModel.registerBroadcast(context = context)
        onStopOrDispose { }
    }



    Scaffold(topBar = { AppTopBar(title = "Controle do Som") }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            Button(onClick = {
                val permissionCheckResultFineLocation = ContextCompat.checkSelfPermission(
                    context, Manifest.permission.ACCESS_FINE_LOCATION
                )
                val permissionCheckResultCoarseLocation = ContextCompat.checkSelfPermission(
                    context, Manifest.permission.ACCESS_COARSE_LOCATION
                )
                if (permissionCheckResultFineLocation == PackageManager.PERMISSION_GRANTED && permissionCheckResultCoarseLocation == PackageManager.PERMISSION_GRANTED) {
                    soundControlViewModel.startScan()
                } else {
                    permissionLauncher.launch(listPermissions)
                }
            }) {
                Text(text = "Buscar Redes")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogAvailableNetworks(
    closeDialog: () -> Unit, listResults: List<ScanResult>, loading: Boolean
) {
    val configuration = LocalConfiguration.current
    val widthInDp = configuration.screenWidthDp
    val heightInDp = configuration.screenHeightDp
    AlertDialog(
        onDismissRequest = closeDialog
    ) {
        Surface(
            modifier = Modifier
                .width((widthInDp * 0.85).dp)
                .height((heightInDp * 0.5).dp),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (loading) {
                    CircularProgressIndicator(modifier = Modifier.height(55.dp))
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(items = listResults) {
                            Text(text = it.SSID)
                        }
                    }
                }
            }
        }
    }

}