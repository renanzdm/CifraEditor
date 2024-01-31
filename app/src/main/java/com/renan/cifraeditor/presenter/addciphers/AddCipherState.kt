package com.renan.cifraeditor.presenter.addciphers

import com.renan.cifraeditor.domain.entities.tables.Tom

data class AddCipherState(
    var loading: Boolean = false,
    var errorMessage: String? = null,
    var nameMusic: String? = null,
    var nameArtist: String? = null,
    var idTom: Long = 0,
    var letterMusic: String? = null,
    var idCipherCreated:Long?=null
)
