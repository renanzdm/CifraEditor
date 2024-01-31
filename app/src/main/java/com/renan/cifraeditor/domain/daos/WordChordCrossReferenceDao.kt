package com.renan.cifraeditor.domain.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.renan.cifraeditor.domain.entities.tables.WordChordCrossReference

@Dao
interface WordChordCrossReferenceDao {
    @Insert
    suspend fun insert(entity: WordChordCrossReference)

    @Query("DELETE FROM WordChordCrossReference WHERE WordChordCrossReference.chordId = :chordId  AND WordChordCrossReference.wordId = :wordId")
    suspend fun delete(chordId: Long, wordId: Long)

    @Query("UPDATE WordChordCrossReference SET chordId = :newChordId  WHERE WordChordCrossReference.wordId = :wordId")
    suspend fun updateChordWordCrossReference(newChordId: Long, wordId: Long)

    @Query("DELETE FROM WordChordCrossReference WHERE WordChordCrossReference.wordId in (:idsWords)")
    suspend fun deleteByWordIds(idsWords:List<Long>)

    @Query("DELETE FROM WordChordCrossReference WHERE WordChordCrossReference.wordId == :id")
    suspend fun deleteByWordId(id:Long)



}