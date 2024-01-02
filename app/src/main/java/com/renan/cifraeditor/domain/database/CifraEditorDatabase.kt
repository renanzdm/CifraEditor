package com.renan.cifraeditor.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.renan.cifraeditor.domain.daos.ChordDao
import com.renan.cifraeditor.domain.entities.tables.Chord
import com.renan.cifraeditor.domain.entities.tables.Cipher
import com.renan.cifraeditor.domain.entities.tables.Tom
import com.renan.cifraeditor.domain.entities.tables.Word
import com.renan.cifraeditor.domain.daos.CipherDao
import com.renan.cifraeditor.domain.daos.TomDao
import com.renan.cifraeditor.domain.daos.WordChordCrossReferenceDao
import com.renan.cifraeditor.domain.daos.WordDao
import com.renan.cifraeditor.domain.entities.tables.WordChordCrossReference

@Database(
    entities = [Tom::class, Cipher::class, Word::class, Chord::class, WordChordCrossReference::class],
    version = 1,
    exportSchema = false,
)
abstract class CifraEditorDatabase : RoomDatabase() {
    abstract fun tomDao(): TomDao
    abstract fun cipherDao(): CipherDao

    abstract fun wordsDao(): WordDao
    abstract fun chordDao(): ChordDao
    abstract fun wordChordCrossReferenceDao(): WordChordCrossReferenceDao
}


val toms: List<Tom> = listOf(
    Tom(1, "C"),
    Tom(2, "C#"),
    Tom(3, "D"),
    Tom(4, "Eb"),
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
    Tom(16, "Ebm"),
    Tom(17, "Em"),
    Tom(18, "Fm"),
    Tom(19, "F#m"),
    Tom(20, "Gm"),
    Tom(21, "G#m"),
    Tom(22, "Am"),
    Tom(23, "A#m"),
    Tom(24, "Bm"),
)

val chords: List<Chord> = listOf(
    //C
    Chord(chordName = "C7M", chordDegree = 1, fkTom = 1),
    Chord(chordName = "Dm7", chordDegree = 2, fkTom = 1),
    Chord(chordName = "Em7", chordDegree = 3, fkTom = 1),
    Chord(chordName = "F7M", chordDegree = 4, fkTom = 1),
    Chord(chordName = "G7", chordDegree = 5, fkTom = 1),
    Chord(chordName = "Am7", chordDegree = 6, fkTom = 1),
    Chord(chordName = "Bm7(b5)", chordDegree = 7, fkTom = 1),

    //C#
    Chord(chordName = "C#7M", chordDegree = 1, fkTom = 2),
    Chord(chordName = "D#m7", chordDegree = 2, fkTom = 2),
    Chord(chordName = "E#m7", chordDegree = 3, fkTom = 2),
    Chord(chordName = "F#7M", chordDegree = 4, fkTom = 2),
    Chord(chordName = "G#7", chordDegree = 5, fkTom = 2),
    Chord(chordName = "A#m7", chordDegree = 6, fkTom = 2),
    Chord(chordName = "B#m7(b5)", chordDegree = 7, fkTom = 2),

    //D
    Chord(chordName = "D7M", chordDegree = 1, fkTom = 3),
    Chord(chordName = "Em7", chordDegree = 2, fkTom = 3),
    Chord(chordName = "F#m7", chordDegree = 3, fkTom = 3),
    Chord(chordName = "G7", chordDegree = 4, fkTom = 3),
    Chord(chordName = "A7M", chordDegree = 5, fkTom = 3),
    Chord(chordName = "Bm7", chordDegree = 6, fkTom = 3),
    Chord(chordName = "C#m7(b5)", chordDegree = 7, fkTom = 3),

    //Eb
    Chord(chordName = "Eb7M", chordDegree = 1, fkTom = 4),
    Chord(chordName = "Fm7", chordDegree = 2, fkTom = 4),
    Chord(chordName = "Gm7", chordDegree = 3, fkTom = 4),
    Chord(chordName = "Ab7M", chordDegree = 4, fkTom = 4),
    Chord(chordName = "Bb7", chordDegree = 5, fkTom = 4),
    Chord(chordName = "Cm7", chordDegree = 6, fkTom = 4),
    Chord(chordName = "Dm7(b5)", chordDegree = 7, fkTom = 4),


    //E
    Chord(chordName = "E7M", chordDegree = 1, fkTom = 5),
    Chord(chordName = "F#m7", chordDegree = 2, fkTom = 5),
    Chord(chordName = "G#m7", chordDegree = 3, fkTom = 5),
    Chord(chordName = "A7M", chordDegree = 4, fkTom = 5),
    Chord(chordName = "B7", chordDegree = 5, fkTom = 5),
    Chord(chordName = "C#m7", chordDegree = 6, fkTom = 5),
    Chord(chordName = "D#m7(b5)", chordDegree = 7, fkTom = 5),

    //F
    Chord(chordName = "F7M", chordDegree = 1, fkTom = 6),
    Chord(chordName = "Gm7", chordDegree = 2, fkTom = 6),
    Chord(chordName = "Am7", chordDegree = 3, fkTom = 6),
    Chord(chordName = "BbM", chordDegree = 4, fkTom = 6),
    Chord(chordName = "C7", chordDegree = 5, fkTom = 6),
    Chord(chordName = "Dm7", chordDegree = 6, fkTom = 6),
    Chord(chordName = "Em7(b5)", chordDegree = 7, fkTom = 6),


    //F#
    Chord(chordName = "F#7M", chordDegree = 1, fkTom = 7),
    Chord(chordName = "G#m7", chordDegree = 2, fkTom = 7),
    Chord(chordName = "A#m7", chordDegree = 3, fkTom = 7),
    Chord(chordName = "B7M", chordDegree = 4, fkTom = 7),
    Chord(chordName = "C#7", chordDegree = 5, fkTom = 7),
    Chord(chordName = "D#m7", chordDegree = 6, fkTom = 7),
    Chord(chordName = "Fm7(b5)", chordDegree = 7, fkTom = 7),

    //G
    Chord(chordName = "G7M", chordDegree = 1, fkTom = 8),
    Chord(chordName = "Am7", chordDegree = 2, fkTom = 8),
    Chord(chordName = "Bm7", chordDegree = 3, fkTom = 8),
    Chord(chordName = "C7M", chordDegree = 4, fkTom = 8),
    Chord(chordName = "D7", chordDegree = 5, fkTom = 8),
    Chord(chordName = "Em7", chordDegree = 6, fkTom = 8),
    Chord(chordName = "F#m7(b5)", chordDegree = 7, fkTom = 8),


    //G#
    Chord(chordName = "G#7M", chordDegree = 1, fkTom = 9),
    Chord(chordName = "A#m7", chordDegree = 2, fkTom = 9),
    Chord(chordName = "Cm7", chordDegree = 3, fkTom = 9),
    Chord(chordName = "C#7M", chordDegree = 4, fkTom = 9),
    Chord(chordName = "D#7", chordDegree = 5, fkTom = 9),
    Chord(chordName = "Fm7", chordDegree = 6, fkTom = 9),
    Chord(chordName = "Gm7(b5)", chordDegree = 7, fkTom = 9),


    //A
    Chord(chordName = "A7M", chordDegree = 1, fkTom = 10),
    Chord(chordName = "Bm7", chordDegree = 2, fkTom = 10),
    Chord(chordName = "C#m7", chordDegree = 3, fkTom = 10),
    Chord(chordName = "D7M", chordDegree = 4, fkTom = 10),
    Chord(chordName = "E7", chordDegree = 5, fkTom = 10),
    Chord(chordName = "F#m7", chordDegree = 6, fkTom = 10),
    Chord(chordName = "G#m7(b5)", chordDegree = 7, fkTom = 10),

    //A#
    Chord(chordName = "A#7M", chordDegree = 1, fkTom = 11),
    Chord(chordName = "Cm7", chordDegree = 2, fkTom = 11),
    Chord(chordName = "Dm7", chordDegree = 3, fkTom = 11),
    Chord(chordName = "Eb7M", chordDegree = 4, fkTom = 11),
    Chord(chordName = "F7", chordDegree = 5, fkTom = 11),
    Chord(chordName = "Gm7", chordDegree = 6, fkTom = 11),
    Chord(chordName = "Am7(b5)", chordDegree = 7, fkTom = 11),


    //B
    Chord(chordName = "B7M", chordDegree = 1, fkTom = 12),
    Chord(chordName = "C#m7", chordDegree = 2, fkTom = 12),
    Chord(chordName = "D#m7", chordDegree = 3, fkTom = 12),
    Chord(chordName = "E7M", chordDegree = 4, fkTom = 12),
    Chord(chordName = "F#7", chordDegree = 5, fkTom = 12),
    Chord(chordName = "G#m7", chordDegree = 6, fkTom = 12),
    Chord(chordName = "A#m7(b5)", chordDegree = 7, fkTom = 12),


    //---MENORES--//

    //G#m
    Chord(chordName = "G#m7", chordDegree = 1, fkTom = 21),
    Chord(chordName = "A#m7(b5)", chordDegree = 2, fkTom = 21),
    Chord(chordName = "B7M", chordDegree = 3, fkTom = 21),
    Chord(chordName = "C#m7", chordDegree = 4, fkTom = 21),
    Chord(chordName = "D#m7", chordDegree = 5, fkTom = 21),
    Chord(chordName = "E7M", chordDegree = 6, fkTom = 21),
    Chord(chordName = "F#7", chordDegree = 7, fkTom = 21),
    //Dm
    Chord(chordName = "Dm7", chordDegree = 1, fkTom = 15),
    Chord(chordName = "Em7(b5)", chordDegree = 2, fkTom = 15),
    Chord(chordName = "F7M", chordDegree = 3, fkTom = 15),
    Chord(chordName = "Gm7", chordDegree = 4, fkTom = 15),
    Chord(chordName = "Am7", chordDegree = 5, fkTom = 15),
    Chord(chordName = "BbM", chordDegree = 6, fkTom = 15),
    Chord(chordName = "C7", chordDegree = 7, fkTom = 15),

    //Am
    Chord(chordName = "Am7", chordDegree = 1, fkTom = 22),
    Chord(chordName = "Bm7(b5)", chordDegree = 2, fkTom = 22),
    Chord(chordName = "C7M", chordDegree = 3, fkTom = 22),
    Chord(chordName = "Dm7", chordDegree = 4, fkTom = 22),
    Chord(chordName = "Em7", chordDegree = 5, fkTom = 22),
    Chord(chordName = "F7M", chordDegree = 6, fkTom = 22),
    Chord(chordName = "G7", chordDegree = 7, fkTom = 22),

    //A#m
    Chord(chordName = "A#m7", chordDegree = 1, fkTom = 23),
    Chord(chordName = "B#m7(b5)", chordDegree = 2, fkTom = 23),
    Chord(chordName = "C#7M", chordDegree = 3, fkTom = 23),
    Chord(chordName = "D#m7", chordDegree = 4, fkTom = 23),
    Chord(chordName = "E#m7", chordDegree = 5, fkTom = 23),
    Chord(chordName = "F#7M", chordDegree = 6, fkTom = 23),
    Chord(chordName = "G#7", chordDegree = 7, fkTom = 23),
    //Bm
    Chord(chordName = "Bm7", chordDegree = 1, fkTom = 24),
    Chord(chordName = "C#m7(b5)", chordDegree = 2, fkTom = 24),
    Chord(chordName = "D7M", chordDegree = 3, fkTom = 24),
    Chord(chordName = "Em7", chordDegree = 4, fkTom = 24),
    Chord(chordName = "F#m7", chordDegree = 5, fkTom = 24),
    Chord(chordName = "G7", chordDegree = 6, fkTom = 24),
    Chord(chordName = "A7M", chordDegree = 7, fkTom = 24),

    //Ebm
    Chord(chordName = "Ebm7", chordDegree = 1, fkTom = 16),
    Chord(chordName = "Fm7(b5)", chordDegree = 2, fkTom = 16),
    Chord(chordName = "F#7M", chordDegree = 3, fkTom = 16),
    Chord(chordName = "G#   m7", chordDegree = 4, fkTom = 16),
    Chord(chordName = "A#m7", chordDegree = 5, fkTom = 16),
    Chord(chordName = "B7M", chordDegree = 6, fkTom = 16),
    Chord(chordName = "C#7", chordDegree = 7, fkTom = 16),


    //Em
    Chord(chordName = "Em7", chordDegree = 1, fkTom = 17),
    Chord(chordName = "F#m7(b5)", chordDegree = 2, fkTom = 17),
    Chord(chordName = "G7M", chordDegree = 3, fkTom = 17),
    Chord(chordName = "Am7", chordDegree = 4, fkTom = 17),
    Chord(chordName = "Bm7", chordDegree = 5, fkTom = 17),
    Chord(chordName = "C7M", chordDegree = 6, fkTom = 17),
    Chord(chordName = "D7", chordDegree = 7, fkTom = 17),


    //F#m
    Chord(chordName = "F#m7", chordDegree = 1, fkTom = 19),
    Chord(chordName = "G#m7(b5)", chordDegree = 2, fkTom = 19),
    Chord(chordName = "A7M", chordDegree = 3, fkTom = 19),
    Chord(chordName = "Bm7", chordDegree = 4, fkTom = 19),
    Chord(chordName = "C#m7", chordDegree = 5, fkTom = 19),
    Chord(chordName = "D7M", chordDegree = 6, fkTom = 19),
    Chord(chordName = "E7", chordDegree = 7, fkTom = 19),

    //Gm
    Chord(chordName = "Gm7", chordDegree = 1, fkTom = 20),
    Chord(chordName = "Am7(b5)", chordDegree = 2, fkTom = 20),
    Chord(chordName = "A#7M", chordDegree = 3, fkTom = 20),
    Chord(chordName = "Cm7", chordDegree = 4, fkTom = 20),
    Chord(chordName = "Dm7", chordDegree = 5, fkTom = 20),
    Chord(chordName = "Eb7M", chordDegree = 6, fkTom = 20),
    Chord(chordName = "F7", chordDegree = 7, fkTom = 20),

    //Fm
    Chord(chordName = "Fm7", chordDegree = 1, fkTom = 18),
    Chord(chordName = "Gm7(b5)", chordDegree = 2, fkTom = 18),
    Chord(chordName = "G#7M", chordDegree = 3, fkTom = 18),
    Chord(chordName = "A#m7", chordDegree = 4, fkTom = 18),
    Chord(chordName = "Cm7", chordDegree = 5, fkTom = 18),
    Chord(chordName = "C#7M", chordDegree = 6, fkTom = 18),
    Chord(chordName = "D#7", chordDegree = 7, fkTom = 18),

    //Cm
    Chord(chordName = "Cm7", chordDegree = 1, fkTom = 13),
    Chord(chordName = "Dm7(b5)", chordDegree = 2, fkTom = 13),
    Chord(chordName = "Eb7M", chordDegree = 3, fkTom = 13),
    Chord(chordName = "Fm7", chordDegree = 4, fkTom = 13),
    Chord(chordName = "Gm7", chordDegree = 5, fkTom = 13),
    Chord(chordName = "Ab7M", chordDegree = 6, fkTom = 13),
    Chord(chordName = "Bb7", chordDegree = 7, fkTom = 13),


    //C#m
    Chord(chordName = "C#m7", chordDegree = 1, fkTom = 14),
    Chord(chordName = "D#m7(b5)", chordDegree = 2, fkTom = 14),
    Chord(chordName = "E7M", chordDegree = 3, fkTom = 14),
    Chord(chordName = "F#m7", chordDegree = 4, fkTom = 14),
    Chord(chordName = "G#m7", chordDegree = 5, fkTom = 14),
    Chord(chordName = "A7M", chordDegree = 6, fkTom = 14),
    Chord(chordName = "B7", chordDegree = 7, fkTom = 14),


    )
