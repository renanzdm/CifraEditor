package com.renan.cifraeditor.domain.services.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.renan.cifraeditor.data.tables.Tom

interface TomDao {
    @Query("SELECT * FROM tom")
    suspend fun getAll(): List<Tom>

    @Query("SELECT * FROM tom WHERE id IN (:ids)")
    suspend fun loadAllByIds(ids: IntArray): List<Tom>

    @Insert
    suspend fun insertAll(toms: Tom)

    @Delete
    suspend fun delete(tom: Tom)
}