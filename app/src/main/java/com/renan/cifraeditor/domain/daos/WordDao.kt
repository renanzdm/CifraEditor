package com.renan.cifraeditor.domain.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.renan.cifraeditor.data.tables.Cipher
import com.renan.cifraeditor.data.tables.Word
@Dao
interface WordDao {


    @Query("SELECT * FROM word")
    suspend fun getAll(): List<Word>

    @Insert
    suspend fun insert(word: Word):Long

    @Delete
    suspend fun delete(word: Word)

    @Insert
    suspend fun insertAllWords(word: List<Word>)
}