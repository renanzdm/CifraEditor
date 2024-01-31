package com.renan.cifraeditor.domain.entities.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tom(
    @PrimaryKey val tomId: Long? = null,
    @ColumnInfo val tomName: String,
    @ColumnInfo val major: Boolean,
    @ColumnInfo var tonic: String,
    @ColumnInfo var interval:Int
    
)
