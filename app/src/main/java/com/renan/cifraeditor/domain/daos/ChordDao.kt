package com.renan.cifraeditor.domain.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.renan.cifraeditor.domain.entities.tables.Chord

@Dao

interface ChordDao {
    @Query("SELECT * FROM CHORD")
    suspend fun getAll():List<Chord>
    @Insert
    suspend fun insert(chord: Chord)

    @Query("SELECT * FROM Chord WHERE fkTom = :idTom")
    suspend fun getByTom(idTom:Long):List<Chord>




}