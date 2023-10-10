package com.renan.cifraeditor.data.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cipher(
    @PrimaryKey val id: Int,
    @ColumnInfo var name: String,
    @ColumnInfo var artist: String? = null,
    @ColumnInfo(name = "fk_tom") var fkTom: Int
)
