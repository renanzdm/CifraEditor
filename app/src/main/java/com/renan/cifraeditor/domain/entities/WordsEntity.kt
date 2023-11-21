package com.renan.cifraeditor.domain.entities

data class WordsEntity(
    val id:Int?=null,
    var name:String,
    val fkChord:Int?=null,
    val fkChiper:Long
)
