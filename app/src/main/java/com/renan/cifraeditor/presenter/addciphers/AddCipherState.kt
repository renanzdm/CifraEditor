package com.renan.cifraeditor.presenter.addciphers

import com.renan.cifraeditor.domain.entities.TomEntity

data class AddCipherState(
    var loading:Boolean = false,
    var allToms:List<TomEntity> = emptyList(),
    var errorMessage:String? = null
)
