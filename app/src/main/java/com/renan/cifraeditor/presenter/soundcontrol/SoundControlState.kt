package com.renan.cifraeditor.presenter.soundcontrol

import android.net.wifi.ScanResult

data class SoundControlState(
    var loading:Boolean = false,
    var openDialog:Boolean = false,
    var availableNetwork: List<ScanResult> = emptyList()
)
