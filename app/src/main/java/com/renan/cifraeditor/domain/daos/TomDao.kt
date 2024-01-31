package com.renan.cifraeditor.domain.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.renan.cifraeditor.domain.entities.tables.Tom

@Dao
interface TomDao {
    @Query("SELECT * FROM Tom")
    suspend fun getAll(): List<Tom>

    @Insert
    suspend fun insert(tom: Tom)

    @Delete
    suspend fun delete(tom: Tom)

    @Query("SELECT * FROM Tom WHERE tomId = :id")
    suspend fun getById(id: Long) : Tom




}