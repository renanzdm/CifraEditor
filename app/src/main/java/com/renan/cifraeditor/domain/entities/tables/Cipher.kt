package com.renan.cifraeditor.domain.entities.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cipher(
    @PrimaryKey val cipherId: Long?=null,
    @ColumnInfo var cipherName: String,
    @ColumnInfo var cipherArtist: String? = null,
    @ColumnInfo var fkTom: Long
)
