package com.renan.cifraeditor.domain.entities.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
    @PrimaryKey val wordId: Long? = null,
    @ColumnInfo var wordName: String,
    @ColumnInfo val fkChiper: Long,
    @ColumnInfo val order : Int,
    @ColumnInfo val numberLine : Int
)
