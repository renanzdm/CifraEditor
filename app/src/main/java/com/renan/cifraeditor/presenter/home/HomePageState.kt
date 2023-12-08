package com.renan.cifraeditor.presenter.home

import com.renan.cifraeditor.domain.entities.tables.Cipher

data class HomePageState(
    var loading: Boolean = false,
    var errorMessage: String? = null,
    var ciphers:List<Cipher> = emptyList()
)