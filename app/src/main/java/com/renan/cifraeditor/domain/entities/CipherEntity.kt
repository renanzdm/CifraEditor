package com.renan.cifraeditor.domain.entities

data class CipherEntity(
    val id:Int,
    var name: String,
    var artist:String?=null,
    var fkTom:Int
)
