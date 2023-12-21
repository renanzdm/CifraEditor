package com.renan.cifraeditor.presenter.cipherdetails

import com.renan.cifraeditor.domain.entities.entitiesrelations.WordWithChords
import com.renan.cifraeditor.domain.entities.tables.Word

data class CipherDetailsState(
    var loading: Boolean = false,
    var errorMessage: String? = null,
    var idCipher:Long?=null ,
    var name:String?=null,
    var artist:String?=null ,
    var fkTom:Long?=null,
    var words:List<WordWithChords> = emptyList(),
    var wordsFormatted:List<List<WordWithChords>> = emptyList()
)