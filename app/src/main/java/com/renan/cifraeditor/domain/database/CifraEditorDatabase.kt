package com.renan.cifraeditor.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.renan.cifraeditor.data.tables.Tom

@Database(entities = [Tom::class], version = 1)
abstract class CifraEditorDatabase : RoomDatabase()  {

}