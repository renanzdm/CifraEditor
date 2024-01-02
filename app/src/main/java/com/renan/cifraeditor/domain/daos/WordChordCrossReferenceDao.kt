package com.renan.cifraeditor.domain.daos

import androidx.room.Dao
import androidx.room.Insert
import com.renan.cifraeditor.domain.entities.tables.WordChordCrossReference

@Dao
interface WordChordCrossReferenceDao {
    @Insert
    suspend fun insert(entity: WordChordCrossReference)


}