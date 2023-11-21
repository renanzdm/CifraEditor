package com.renan.cifraeditor.domain.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.renan.cifraeditor.data.tables.Cipher
import com.renan.cifraeditor.data.tables.Tom

@Dao
interface CipherDao {

    @Query("SELECT * FROM cipher")
    suspend fun getAll(): List<Cipher>

    @Insert
    suspend fun insert(cipher: Cipher):Long

    @Delete
    suspend fun delete(cipher: Cipher)


}