package com.renan.cifraeditor.data.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Chord(
    @PrimaryKey val id: Int?=null,
    @ColumnInfo var name: String,
    @ColumnInfo var degree: Int,
    @ColumnInfo(name = "fk_tom") val fkTom: Int
)
