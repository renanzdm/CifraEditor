package com.renan.cifraeditor.domain.entities

data class ChordEntity(
    val id:Int,
    var name:String,
    var degree:Int,
    val fkTom:Int
)
