package com.renan.cifraeditor.presenter.soundcontrol

import android.Manifest
import android.content.pm.PackageManager
import android.net.LinkProperties
import android.net.wifi.ScanResult
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
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
        }
    val listPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
    )
    LifecycleStartEffect(lifecycleOwner = LocalLifecycleOwner.current) {
        onStopOrDispose { }
    }
    Scaffold(topBar = { AppTopBar(title = "Controle do Som") }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            Button(onClick = {
                val currentNetwork =
                    soundControlViewModel.getConnectivityManager(context).activeNetwork
               val properties:LinkProperties? =  soundControlViewModel.getConnectivityManager(context)
                    .getLinkProperties(currentNetwork)
                println(properties?.linkAddresses)

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
    val heightInDp = configuration.screenHeightDp
    val scrollState = rememberScrollState()
    AlertDialog(
        onDismissRequest = closeDialog
    ) {
        Surface(
            modifier = Modifier.height((heightInDp * 0.5).dp),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .verticalScroll(state = scrollState),
            ) {
                item {
                    if (loading) {
                        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                    }
                }
                items(items = listResults) {
                    Row(modifier = Modifier.padding(vertical = 8.dp)) {
                        IconWifiFrequency(it.level)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = it.SSID)
                    }
                }
            }
        }
    }
}

@Composable
fun IconWifiFrequency(level: Int) {
    val size = 50.dp
    val pathFirstLine by remember {
        mutableStateOf(Path())
    }
    val pathSecondLine by remember {
        mutableStateOf(Path())
    }
    val pathThirdLine by remember {
        mutableStateOf(Path())
    }
    Canvas(
        modifier = Modifier.padding(end = 20.dp)
    ) {
        ///firstLine
        pathFirstLine.moveTo(0f, (size.value * 0.5).toFloat())
        pathFirstLine.lineTo(0f, (size.value * 0.5).toFloat())
        pathFirstLine.quadraticBezierTo(
            (size.value * 0.5).toFloat(), 4f, size.value, (size.value * 0.5).toFloat()
        )
        ///secondLine
        pathSecondLine.moveTo((size.value * 0.2).toFloat(), (size.value * 0.8).toFloat())
        pathSecondLine.lineTo((size.value * 0.2).toFloat(), (size.value * 0.8).toFloat())
        pathSecondLine.quadraticBezierTo(
            ((size.value) * 0.5).toFloat(),
            30f,
            (size.value * 0.8).toFloat(),
            (size.value * 0.8).toFloat()
        )
        //thirdLine
        pathThirdLine.moveTo((size.value * 0.4).toFloat(), size.value)
        pathThirdLine.lineTo((size.value * 0.4).toFloat(), size.value)
        pathThirdLine.quadraticBezierTo(
            (size.value * 0.5).toFloat(),
            45f,
            (size.value * 0.6).toFloat(),
            size.value
        )
        drawPath(
            pathFirstLine,
            color = if (level >= -40) Color.White else Color.White.copy(alpha = 0.6f),
            style = Stroke(
                width = 5f, cap = StrokeCap.Round
            )
        )
        drawPath(
            pathSecondLine,
            color = if (level >= -70) Color.White else Color.White.copy(alpha = 0.6f),
            style = Stroke(
                width = 5f, cap = StrokeCap.Round
            )
        )
        drawPath(
            pathThirdLine, color = Color.White, style = Stroke(
                width = 5f, cap = StrokeCap.Round
            )
        )
    }
}

