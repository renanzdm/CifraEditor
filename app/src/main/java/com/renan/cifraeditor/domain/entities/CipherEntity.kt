package com.renan.cifraeditor.domain.entities

data class CipherEntity(
    val id:Long?=null,
    var name: String,
    var artist:String?=null,
    var fkTom:Long
)
