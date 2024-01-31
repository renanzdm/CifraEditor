package com.renan.cifraeditor.domain.entities.tables

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity( indices = [Index(value = ["wordId"])])
data class WordChordCrossReference(
    @PrimaryKey val wcId:Long?=null,
    val chordId: Long? = null,
    val wordId: Long?=null
)
