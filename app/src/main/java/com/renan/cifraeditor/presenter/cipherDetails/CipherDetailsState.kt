package com.renan.cifraeditor.presenter.cipherDetails

import com.renan.cifraeditor.domain.entities.CipherEntity
import com.renan.cifraeditor.domain.entities.WordsEntity

data class CipherDetailsState(
    var loading: Boolean = false,
    var errorMessage: String? = null,
    var idCipher:Long?=null ,
    var name:String?=null,
    var artist:String?=null ,
    var fkTom:Long?=null,
    var words:List<WordsEntity> = emptyList()
)