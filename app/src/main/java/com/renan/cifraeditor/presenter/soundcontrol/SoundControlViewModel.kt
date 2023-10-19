package com.renan.cifraeditor.presenter.soundcontrol

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SoundControlViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(SoundControlState())
    var state = _state.asStateFlow()
    private lateinit var wifiManager: WifiManager
    private val wifiScanReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val success: Boolean = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
            if (success) {
                scanSuccess()
            } else {
                scanFailure()
            }
        }
    }

    fun registerBroadcast(context: Context) {
        val intentFilter = IntentFilter()
        wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        context.registerReceiver(wifiScanReceiver, intentFilter)

    }

    fun startScan() {
        _state.update { it.copy(openDialog = true, loading = true) }
        wifiManager.startScan()
    }

    @SuppressLint("MissingPermission")
    private fun scanSuccess() {
        _state.update { it.copy(loading = false) }
        val results = wifiManager.scanResults
        _state.update { it.copy(availableNetwork = results) }
    }

    private fun scanFailure() {

    }

    fun closeDialogResults() {
        _state.update { it.copy(openDialog = false) }
    }


}