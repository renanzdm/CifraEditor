package com.renan.cifraeditor.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.renan.cifraeditor.domain.entities.tables.Chord
import com.renan.cifraeditor.domain.entities.tables.Cipher
import com.renan.cifraeditor.domain.entities.tables.Tom
import com.renan.cifraeditor.domain.entities.tables.Word
import com.renan.cifraeditor.domain.daos.CipherDao
import com.renan.cifraeditor.domain.daos.TomDao
import com.renan.cifraeditor.domain.daos.WordDao
import com.renan.cifraeditor.domain.entities.tables.WordChordCrossReference

@Database(
    entities = [Tom::class, Cipher::class, Word::class, Chord::class, WordChordCrossReference::class],
    version = 1,
    exportSchema = false,
)
abstract class CifraEditorDatabase : RoomDatabase() {
    abstract fun tomDao(): TomDao
    abstract fun cipherDao():CipherDao

    abstract fun wordsDao():WordDao
}


val toms: List<Tom> = listOf(
    Tom(1, "C"),
    Tom(2, "C#"),
    Tom(3, "D"),
    Tom(4, "D#"),
    Tom(5, "E"),
    Tom(6, "F"),
    Tom(7, "F#"),
    Tom(8, "G"),
    Tom(9, "G#"),
    Tom(10, "A"),
    Tom(11, "A#"),
    Tom(12, "B"),
    Tom(13, "Cm"),
    Tom(14, "C#m"),
    Tom(15, "Dm"),
    Tom(16, "D#m"),
    Tom(17, "Em"),
    Tom(18, "Fm"),
    Tom(19, "F#m"),
    Tom(20, "Gm"),
    Tom(21, "G#m"),
    Tom(22, "Am"),
    Tom(23, "A#m"),
    Tom(24, "Bm")
)
