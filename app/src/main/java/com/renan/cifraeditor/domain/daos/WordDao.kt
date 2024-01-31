package com.renan.cifraeditor.domain.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.renan.cifraeditor.domain.entities.tables.Word
import com.renan.cifraeditor.domain.entities.tables.WordChordCrossReference

@Dao
interface WordDao {


    @Query("SELECT * FROM word")
    suspend fun getAll(): List<Word>

    @Insert
    suspend fun insert(word: Word):Long

    @Delete
    suspend fun delete(word: Word)

    @Insert
    suspend fun insertAllWords(word: List<Word>):List<Long>

    @Insert
    suspend fun insertAllWordsChords(word: List<WordChordCrossReference>):List<Long>

    @Update
    suspend fun updateWord(word: Word)

    @Query("DELETE FROM Word WHERE Word.fkChiper ==:id")
    suspend fun deleteByIdCipher(id:Long)

}