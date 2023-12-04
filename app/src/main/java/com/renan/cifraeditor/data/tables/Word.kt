package com.renan.cifraeditor.data.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
   @PrimaryKey val id:Int?=null,
   @ColumnInfo  var name:String,
    @ColumnInfo(name = "fk_chord") val fkChord:Int?,
   @ColumnInfo(name = "fk_chipher")  val fkChiper:Long
)
