package com.renan.cifraeditor.presenter.addciphers

import com.renan.cifraeditor.data.tables.Word
import com.renan.cifraeditor.domain.entities.CipherEntity
import com.renan.cifraeditor.domain.entities.TomEntity
import com.renan.cifraeditor.domain.entities.WordsEntity

data class AddCipherState(
    var loading: Boolean = false,
    var allToms: List<TomEntity> = emptyList(),
    var errorMessage: String? = null,
    var nameMusic: String? = null,
    var nameArtist: String? = null,
    var idTom: Long = 0,
    var letterMusic: String? = null,
    var idCipherCreated:Long?=null
)
