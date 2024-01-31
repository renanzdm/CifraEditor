package com.renan.cifraeditor.presenter.soundcontrol

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.renan.cifraeditor.R
import com.renan.cifraeditor.presenter.ui.components.CustomTopAppBar

@Composable
fun SoundControlPage(
    navController: NavController, soundControlViewModel: SoundControlViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val uiState = soundControlViewModel.state.collectAsStateWithLifecycle()
    var ipConnected by remember { mutableStateOf("") }
    var port by remember { mutableStateOf("10023") }

    LifecycleStartEffect(lifecycleOwner = LocalLifecycleOwner.current) {
        onStopOrDispose { }
    }
    Scaffold(topBar = { CustomTopAppBar(title = "Controle do Som") }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .fillMaxWidth()
                .padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                OutlinedTextField(
                    value = ipConnected,
                    label = { Text("Ip") },
                    onValueChange = { value -> ipConnected = value },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_network_wifi_24),
                            contentDescription = "Ip"
                        )
                    },
                )
                IconButton(onClick = {
                    ipConnected = soundControlViewModel.getWifiConnectedProperties(context) ?: ""
                }) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "Buscar redes")

                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = port,
                label = { Text("Port") },
                onValueChange = { value -> port = value },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_door_sliding_24),
                        contentDescription = "Porta"
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                soundControlViewModel.connectOSCServer(ip = ipConnected, port = port)
            }) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Conectar ao Servidor"
                )
                Text(
                    text = "Conectar", modifier = Modifier.padding(horizontal = 12.dp)
                )
            }

        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DialogAvailableNetworks(
//    closeDialog: () -> Unit, listResults: List<ScanResult>, loading: Boolean
//) {
//    val configuration = LocalConfiguration.current
//    val heightInDp = configuration.screenHeightDp
//    val scrollState = rememberScrollState()
//    AlertDialog(
//        onDismissRequest = closeDialog
//    ) {
//        Surface(
//            modifier = Modifier.height((heightInDp * 0.5).dp),
//            shape = MaterialTheme.shapes.large,
//            tonalElevation = AlertDialogDefaults.TonalElevation
//        ) {
//            LazyColumn(
//                modifier = Modifier
//                    .padding(16.dp)
//                    .fillMaxWidth()
//                    .verticalScroll(state = scrollState),
//            ) {
//
//            }
//        }
//    }
//}


//                val permissionCheckResultFineLocation = ContextCompat.checkSelfPermission(
//                    context, Manifest.permission.ACCESS_FINE_LOCATION
//                )
//                val permissionCheckResultCoarseLocation = ContextCompat.checkSelfPermission(
//                    context, Manifest.permission.ACCESS_COARSE_LOCATION
//                )
//                if (permissionCheckResultFineLocation == PackageManager.PERMISSION_GRANTED && permissionCheckResultCoarseLocation == PackageManager.PERMISSION_GRANTED) {
//                    soundControlViewModel.startScan()
//                } else {
//                    permissionLauncher.launch(listPermissions)
//                }
//    val permissionLauncher =
//        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) {
//        }
//    val listPermissions = arrayOf(
//        Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
//    )
