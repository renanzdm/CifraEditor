package com.renan.cifraeditor.presenter.soundcontrol

import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.InetAddress
import javax.inject.Inject


@HiltViewModel
class SoundControlViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(SoundControlState())
    var state = _state.asStateFlow()


    private fun getConnectivityManager(context: Context) =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


    fun closeDialogResults() {
        _state.update { it.copy(openDialog = false) }
    }

    fun getWifiConnectedProperties(context: Context): String? {
        val currentNetwork = getConnectivityManager(context).activeNetwork
        val properties: LinkProperties? =
            getConnectivityManager(context).getLinkProperties(currentNetwork)
        return properties?.dhcpServerAddress?.toString()?.replace("/", "", false)
    }


    fun connectOSCServer(ip: String, port: String) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                val oscPortOut: OSCPortOut = OSCPortOut(InetAddress.getByName(ip), port.toInt())
//                oscPortOut.connect()
//                oscPortOut.send(OSCMessage("/cs/chan/select/1"))
//                oscPortOut.send(OSCMessage("/cs/chan/at/40"))
//            }
//        }
    }


}