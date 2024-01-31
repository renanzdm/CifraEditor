package com.renan.cifraeditor.domain.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.renan.cifraeditor.domain.entities.entitiesrelations.CipherWithWordsAndChords
import com.renan.cifraeditor.domain.entities.tables.Cipher

@Dao
interface CipherDao {

    @Query("SELECT * FROM cipher")
    suspend fun getAll(): List<Cipher>

    @Insert
    suspend fun insert(cipher: Cipher): Long

    @Delete
    suspend fun delete(cipher: Cipher)
    @Transaction
    @Query("SELECT * FROM cipher WHERE cipher.cipherId == :id")
    suspend fun getById(id: Long): CipherWithWordsAndChords

    @Update
    suspend fun update(cipher: Cipher)


}