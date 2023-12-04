package com.renan.cifraeditor.data.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tom(
    @PrimaryKey val id: Long?=null,
    @ColumnInfo val name:String
)
