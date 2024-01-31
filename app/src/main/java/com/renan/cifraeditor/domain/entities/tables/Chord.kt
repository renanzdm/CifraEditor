package com.renan.cifraeditor.domain.entities.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Chord(
    @PrimaryKey val chordId: Long? = null,
    @ColumnInfo var chordName: String,
    @ColumnInfo var tonic: String,
    @ColumnInfo var chordDegree: Int,
    @ColumnInfo val fkTom: Long,
    @ColumnInfo val major: Boolean
)
