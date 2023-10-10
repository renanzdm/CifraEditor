package com.renan.cifraeditor.domain.entities

data class WordsEntity(
    val id:Int,
    var name:String,
    val fkChord:Int,
    val fkChiper:Int
)
