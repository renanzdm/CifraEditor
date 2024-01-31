package com.renan.cifraeditor.domain.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.renan.cifraeditor.domain.daos.ChordDao
import com.renan.cifraeditor.domain.daos.CipherDao
import com.renan.cifraeditor.domain.daos.TomDao
import com.renan.cifraeditor.domain.daos.WordChordCrossReferenceDao
import com.renan.cifraeditor.domain.daos.WordDao
import com.renan.cifraeditor.domain.entities.tables.Chord
import com.renan.cifraeditor.domain.entities.tables.Cipher
import com.renan.cifraeditor.domain.entities.tables.Tom
import com.renan.cifraeditor.domain.entities.tables.Word
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


val allToms: List<Tom> = listOf(
    Tom(1, "C", major = true, tonic = "C", interval = 1),
    Tom(2, "C#", major = true, tonic = "C#", interval = 2),
    Tom(3, "D", major = true, tonic = "D", interval = 3),
    Tom(4, "D#", major = true, tonic = "D#", interval = 4),
    Tom(5, "E", major = true, tonic = "E", interval = 5),
    Tom(6, "F", major = true, tonic = "F", interval = 6),
    Tom(7, "F#", major = true, tonic = "F#", interval = 7),
    Tom(8, "G", major = true, tonic = "G", interval = 8),
    Tom(9, "G#", major = true, tonic = "G#", interval = 9),
    Tom(10, "A", major = true, tonic = "A", interval = 10),
    Tom(11, "A#", major = true, tonic = "A#", interval = 11),
    Tom(12, "B", major = true, tonic = "B", interval = 12),


    Tom(13, "Cm", major = false, tonic = "C", interval = 1),
    Tom(14, "C#m", major = false, tonic = "C#", interval = 2),
    Tom(15, "Dm", major = false, tonic = "D", interval = 3),
    Tom(16, "D#m", major = false, tonic = "D#", interval = 4),
    Tom(17, "Em", major = false, tonic = "E", interval = 5),
    Tom(18, "Fm", major = false, tonic = "F", interval = 6),
    Tom(19, "F#m", major = false, tonic = "F#", interval = 7),
    Tom(20, "Gm", major = false, tonic = "G", interval = 8),
    Tom(21, "G#m", major = false, tonic = "G#", interval = 9),
    Tom(22, "Am", major = false, tonic = "A", interval = 10),
    Tom(23, "A#m", major = false, tonic = "A#", interval = 11),
    Tom(24, "Bm", major = false, tonic = "B", interval = 12),
)

val chords: List<Chord> = listOf(
    //C
    Chord(chordName = "C7M", chordDegree = 1, fkTom = 1, tonic = "C", major = true),
    Chord(chordName = "Dm7", chordDegree = 2, fkTom = 1, tonic = "D", major = false),
    Chord(chordName = "Em7", chordDegree = 3, fkTom = 1, tonic = "E", major = false),
    Chord(chordName = "F7M", chordDegree = 4, fkTom = 1, tonic = "F", major = true),
    Chord(chordName = "G7", chordDegree = 5, fkTom = 1, tonic = "G", major = true),
    Chord(chordName = "Am7", chordDegree = 6, fkTom = 1, tonic = "A", major = false),
    Chord(chordName = "Bm7(b5)", chordDegree = 7, fkTom = 1, tonic = "B", major = false),

    //C#
    Chord(chordName = "C#7M", chordDegree = 1, fkTom = 2, tonic = "C#", major = true),
    Chord(chordName = "D#m7", chordDegree = 2, fkTom = 2, tonic = "D#", major = false),
    Chord(chordName = "E#m7", chordDegree = 3, fkTom = 2, tonic = "F", major = false),
    Chord(chordName = "F#7M", chordDegree = 4, fkTom = 2, tonic = "F#", major = true),
    Chord(chordName = "G#7", chordDegree = 5, fkTom = 2, tonic = "G#", major = true),
    Chord(chordName = "A#m7", chordDegree = 6, fkTom = 2, tonic = "A#", major = false),
    Chord(chordName = "B#m7(b5)", chordDegree = 7, fkTom = 2, tonic = "C", major = false),

    //D
    Chord(chordName = "D7M", chordDegree = 1, fkTom = 3, tonic = "D", major = true),
    Chord(chordName = "Em7", chordDegree = 2, fkTom = 3, tonic = "E", major = false),
    Chord(chordName = "F#m7", chordDegree = 3, fkTom = 3, tonic = "F#", major = false),
    Chord(chordName = "G7", chordDegree = 4, fkTom = 3, tonic = "G", major = true),
    Chord(chordName = "A7M", chordDegree = 5, fkTom = 3, tonic = "A", major = true),
    Chord(chordName = "Bm7", chordDegree = 6, fkTom = 3, tonic = "B", major = false),
    Chord(chordName = "C#m7(b5)", chordDegree = 7, fkTom = 3, tonic = "C#", major = false),

    //D#
    Chord(chordName = "D#7M", chordDegree = 1, fkTom = 4, tonic = "D#", major = true),
    Chord(chordName = "Fm7", chordDegree = 2, fkTom = 4, tonic = "F", major = false),
    Chord(chordName = "Gm7", chordDegree = 3, fkTom = 4, tonic = "G", major = false),
    Chord(chordName = "Ab7M", chordDegree = 4, fkTom = 4, tonic = "G#", major = true),
    Chord(
        chordName = "Bb7", chordDegree = 5, fkTom = 4, tonic = "A#", major = true
    ),
    Chord(chordName = "Cm7", chordDegree = 6, fkTom = 4, tonic = "C", major = false),
    Chord(chordName = "Dm7(b5)", chordDegree = 7, fkTom = 4, tonic = "D", major = false),


    //E
    Chord(chordName = "E7M", chordDegree = 1, fkTom = 5, tonic = "E", major = true),
    Chord(chordName = "F#m7", chordDegree = 2, fkTom = 5, tonic = "F#", major = false),
    Chord(chordName = "G#m7", chordDegree = 3, fkTom = 5, tonic = "G#", major = false),
    Chord(chordName = "A7M", chordDegree = 4, fkTom = 5, tonic = "A", major = true),
    Chord(chordName = "B7", chordDegree = 5, fkTom = 5, tonic = "B", major = true),
    Chord(chordName = "C#m7", chordDegree = 6, fkTom = 5, tonic = "C#", major = false),
    Chord(chordName = "D#m7(b5)", chordDegree = 7, fkTom = 5, tonic = "D#", major = false),

    //F
    Chord(chordName = "F7M", chordDegree = 1, fkTom = 6, tonic = "F", major = true),
    Chord(chordName = "Gm7", chordDegree = 2, fkTom = 6, tonic = "G", major = false),
    Chord(chordName = "Am7", chordDegree = 3, fkTom = 6, tonic = "A", major = false),
    Chord(
        chordName = "BbM", chordDegree = 4, fkTom = 6, tonic = "A#", major = true
    ),
    Chord(chordName = "C7", chordDegree = 5, fkTom = 6, tonic = "C", major = true),
    Chord(chordName = "Dm7", chordDegree = 6, fkTom = 6, tonic = "D", major = false),
    Chord(chordName = "Em7(b5)", chordDegree = 7, fkTom = 6, tonic = "E", major = false),


    //F#
    Chord(chordName = "F#7M", chordDegree = 1, fkTom = 7, tonic = "F#", major = true),
    Chord(chordName = "G#m7", chordDegree = 2, fkTom = 7, tonic = "G#", major = false),
    Chord(
        chordName = "A#m7", chordDegree = 3, fkTom = 7, tonic = "A#", major = false
    ),
    Chord(chordName = "B7M", chordDegree = 4, fkTom = 7, tonic = "B", major = true),
    Chord(chordName = "C#7", chordDegree = 5, fkTom = 7, tonic = "C#", major = true),
    Chord(
        chordName = "D#m7", chordDegree = 6, fkTom = 7, tonic = "D#", major = false
    ),
    Chord(chordName = "Fm7(b5)", chordDegree = 7, fkTom = 7, tonic = "F", major = false),

    //G
    Chord(chordName = "G7M", chordDegree = 1, fkTom = 8, tonic = "G", major = true),
    Chord(chordName = "Am7", chordDegree = 2, fkTom = 8, tonic = "A", major = false),
    Chord(chordName = "Bm7", chordDegree = 3, fkTom = 8, tonic = "B", major = false),
    Chord(chordName = "C7M", chordDegree = 4, fkTom = 8, tonic = "C", major = true),
    Chord(chordName = "D7", chordDegree = 5, fkTom = 8, tonic = "D", major = true),
    Chord(chordName = "Em7", chordDegree = 6, fkTom = 8, tonic = "E", major = false),
    Chord(chordName = "F#m7(b5)", chordDegree = 7, fkTom = 8, tonic = "F#", major = false),


    //G#
    Chord(chordName = "G#7M", chordDegree = 1, fkTom = 9, tonic = "G#", major = true),
    Chord(chordName = "A#m7", chordDegree = 2, fkTom = 9, tonic = "A#", major = false),
    Chord(chordName = "Cm7", chordDegree = 3, fkTom = 9, tonic = "C", major = false),
    Chord(chordName = "C#7M", chordDegree = 4, fkTom = 9, tonic = "C#", major = true),
    Chord(chordName = "D#7", chordDegree = 5, fkTom = 9, tonic = "D#", major = true),
    Chord(chordName = "Fm7", chordDegree = 6, fkTom = 9, tonic = "F", major = false),
    Chord(chordName = "Gm7(b5)", chordDegree = 7, fkTom = 9, tonic = "G", major = false),


    //A
    Chord(chordName = "A7M", chordDegree = 1, fkTom = 10, tonic = "A", major = true),
    Chord(chordName = "Bm7", chordDegree = 2, fkTom = 10, tonic = "B", major = false),
    Chord(chordName = "C#m7", chordDegree = 3, fkTom = 10, tonic = "C#", major = false),
    Chord(chordName = "D7M", chordDegree = 4, fkTom = 10, tonic = "D", major = true),
    Chord(chordName = "E7", chordDegree = 5, fkTom = 10, tonic = "E", major = true),
    Chord(chordName = "F#m7", chordDegree = 6, fkTom = 10, tonic = "F#", major = false),
    Chord(chordName = "G#m7(b5)", chordDegree = 7, fkTom = 10, tonic = "G#", major = false),

    //A#
    Chord(chordName = "A#7M", chordDegree = 1, fkTom = 11, tonic = "A#", major = true),
    Chord(chordName = "Cm7", chordDegree = 2, fkTom = 11, tonic = "C", major = false),
    Chord(chordName = "Dm7", chordDegree = 3, fkTom = 11, tonic = "D", major = false),
    Chord(chordName = "D#7M", chordDegree = 4, fkTom = 11, tonic = "D#", major = true),
    Chord(chordName = "F7", chordDegree = 5, fkTom = 11, tonic = "F", major = true),
    Chord(chordName = "Gm7", chordDegree = 6, fkTom = 11, tonic = "G", major = false),
    Chord(chordName = "Am7(b5)", chordDegree = 7, fkTom = 11, tonic = "A", major = false),


    //B
    Chord(chordName = "B7M", chordDegree = 1, fkTom = 12, tonic = "B", major = true),
    Chord(chordName = "C#m7", chordDegree = 2, fkTom = 12, tonic = "C#", major = false),
    Chord(chordName = "D#m7", chordDegree = 3, fkTom = 12, tonic = "D#", major = false),
    Chord(chordName = "E7M", chordDegree = 4, fkTom = 12, tonic = "E", major = true),
    Chord(chordName = "F#7", chordDegree = 5, fkTom = 12, tonic = "F#", major = true),
    Chord(chordName = "G#m7", chordDegree = 6, fkTom = 12, tonic = "G#", major = false),
    Chord(chordName = "A#m7(b5)", chordDegree = 7, fkTom = 12, tonic = "A#", major = false),


    //---MENORES--//

    //G#m
    Chord(chordName = "G#m7", chordDegree = 1, fkTom = 21, tonic = "G#", major = false),
    Chord(chordName = "A#m7(b5)", chordDegree = 2, fkTom = 21, tonic = "A#", major = false),
    Chord(chordName = "B7M", chordDegree = 3, fkTom = 21, tonic = "B", major = true),
    Chord(chordName = "C#m7", chordDegree = 4, fkTom = 21, tonic = "C#", major = false),
    Chord(chordName = "D#m7", chordDegree = 5, fkTom = 21, tonic = "D#", major = false),
    Chord(chordName = "E7M", chordDegree = 6, fkTom = 21, tonic = "E", major = true),
    Chord(chordName = "F#7", chordDegree = 7, fkTom = 21, tonic = "F#", major = true),
    //Dm
    Chord(chordName = "Dm7", chordDegree = 1, fkTom = 15, tonic = "D", major = false),
    Chord(chordName = "Em7(b5)", chordDegree = 2, fkTom = 15, tonic = "E", major = false),
    Chord(chordName = "F7M", chordDegree = 3, fkTom = 15, tonic = "F", major = true),
    Chord(chordName = "Gm7", chordDegree = 4, fkTom = 15, tonic = "G", major = false),
    Chord(chordName = "Am7", chordDegree = 5, fkTom = 15, tonic = "A", major = false),
    Chord(chordName = "BbM", chordDegree = 6, fkTom = 15, tonic = "A#", major = true),
    Chord(chordName = "C7", chordDegree = 7, fkTom = 15, tonic = "C", major = true),

    //Am
    Chord(chordName = "Am7", chordDegree = 1, fkTom = 22, tonic = "A", major = false),
    Chord(chordName = "Bm7(b5)", chordDegree = 2, fkTom = 22, tonic = "B", major = false),
    Chord(chordName = "C7M", chordDegree = 3, fkTom = 22, tonic = "C", major = true),
    Chord(chordName = "Dm7", chordDegree = 4, fkTom = 22, tonic = "D", major = false),
    Chord(chordName = "Em7", chordDegree = 5, fkTom = 22, tonic = "E", major = false),
    Chord(chordName = "F7M", chordDegree = 6, fkTom = 22, tonic = "F", major = true),
    Chord(chordName = "G7", chordDegree = 7, fkTom = 22, tonic = "G", major = true),

    //A#m
    Chord(chordName = "A#m7", chordDegree = 1, fkTom = 23, tonic = "A#", major = false),
    Chord(chordName = "B#m7(b5)", chordDegree = 2, fkTom = 23, tonic = "C", major = false),
    Chord(chordName = "C#7M", chordDegree = 3, fkTom = 23, tonic = "C#", major = true),
    Chord(chordName = "D#m7", chordDegree = 4, fkTom = 23, tonic = "D#", major = false),
    Chord(
        chordName = "E#m7", chordDegree = 5, fkTom = 23, tonic = "F", major = false
    ),
    Chord(chordName = "F#7M", chordDegree = 6, fkTom = 23, tonic = "F#", major = true),
    Chord(chordName = "G#7", chordDegree = 7, fkTom = 23, tonic = "G#", major = true),
    //Bm
    Chord(chordName = "Bm7", chordDegree = 1, fkTom = 24, tonic = "B", major = false),
    Chord(chordName = "C#m7(b5)", chordDegree = 2, fkTom = 24, tonic = "C#", major = false),
    Chord(chordName = "D7M", chordDegree = 3, fkTom = 24, tonic = "D", major = true),
    Chord(chordName = "Em7", chordDegree = 4, fkTom = 24, tonic = "E", major = false),
    Chord(chordName = "F#m7", chordDegree = 5, fkTom = 24, tonic = "F#", major = false),
    Chord(chordName = "G7", chordDegree = 6, fkTom = 24, tonic = "G", major = true),
    Chord(chordName = "A7M", chordDegree = 7, fkTom = 24, tonic = "A", major = true),

    //D#m
    Chord(chordName = "D#m7", chordDegree = 1, fkTom = 16, tonic = "D#", major = false),
    Chord(chordName = "Fm7(b5)", chordDegree = 2, fkTom = 16, tonic = "F", major = false),
    Chord(chordName = "F#7M", chordDegree = 3, fkTom = 16, tonic = "F#", major = true),
    Chord(chordName = "G#m7", chordDegree = 4, fkTom = 16, tonic = "G#", major = false),
    Chord(chordName = "A#m7", chordDegree = 5, fkTom = 16, tonic = "A#", major = false),
    Chord(chordName = "B7M", chordDegree = 6, fkTom = 16, tonic = "B", major = true),
    Chord(chordName = "C#7", chordDegree = 7, fkTom = 16, tonic = "C#", major = true),


    //Em
    Chord(chordName = "Em7", chordDegree = 1, fkTom = 17, tonic = "E", major = false),
    Chord(chordName = "F#m7(b5)", chordDegree = 2, fkTom = 17, tonic = "F#", major = false),
    Chord(chordName = "G7M", chordDegree = 3, fkTom = 17, tonic = "G", major = true),
    Chord(chordName = "Am7", chordDegree = 4, fkTom = 17, tonic = "A", major = false),
    Chord(chordName = "Bm7", chordDegree = 5, fkTom = 17, tonic = "B", major = false),
    Chord(chordName = "C7M", chordDegree = 6, fkTom = 17, tonic = "C", major = true),
    Chord(chordName = "D7", chordDegree = 7, fkTom = 17, tonic = "D", major = true),


    //F#m
    Chord(chordName = "F#m7", chordDegree = 1, fkTom = 19, tonic = "F#", major = false),
    Chord(chordName = "G#m7(b5)", chordDegree = 2, fkTom = 19, tonic = "G#", major = false),
    Chord(chordName = "A7M", chordDegree = 3, fkTom = 19, tonic = "A", major = true),
    Chord(chordName = "Bm7", chordDegree = 4, fkTom = 19, tonic = "B", major = false),
    Chord(chordName = "C#m7", chordDegree = 5, fkTom = 19, tonic = "C#", major = false),
    Chord(chordName = "D7M", chordDegree = 6, fkTom = 19, tonic = "D", major = true),
    Chord(chordName = "E7", chordDegree = 7, fkTom = 19, tonic = "E", major = true),

    //Gm
    Chord(chordName = "Gm7", chordDegree = 1, fkTom = 20, tonic = "G", major = false),
    Chord(chordName = "Am7(b5)", chordDegree = 2, fkTom = 20, tonic = "A", major = false),
    Chord(chordName = "A#7M", chordDegree = 3, fkTom = 20, tonic = "A#", major = true),
    Chord(chordName = "Cm7", chordDegree = 4, fkTom = 20, tonic = "C", major = false),
    Chord(chordName = "Dm7", chordDegree = 5, fkTom = 20, tonic = "D", major = false),
    Chord(chordName = "D#7M", chordDegree = 6, fkTom = 20, tonic = "D#", major = true),
    Chord(chordName = "F7", chordDegree = 7, fkTom = 20, tonic = "F", major = true),

    //Fm
    Chord(chordName = "Fm7", chordDegree = 1, fkTom = 18, tonic = "F", major = false),
    Chord(chordName = "Gm7(b5)", chordDegree = 2, fkTom = 18, tonic = "G", major = false),
    Chord(chordName = "G#7M", chordDegree = 3, fkTom = 18, tonic = "G#", major = true),
    Chord(chordName = "A#m7", chordDegree = 4, fkTom = 18, tonic = "A#", major = false),
    Chord(chordName = "Cm7", chordDegree = 5, fkTom = 18, tonic = "C", major = false),
    Chord(chordName = "C#7M", chordDegree = 6, fkTom = 18, tonic = "C#", major = true),
    Chord(chordName = "D#7", chordDegree = 7, fkTom = 18, tonic = "D#", major = true),

    //Cm
    Chord(chordName = "Cm7", chordDegree = 1, fkTom = 13, tonic = "C", major = false),
    Chord(chordName = "Dm7(b5)", chordDegree = 2, fkTom = 13, tonic = "D", major = false),
    Chord(chordName = "D#7M", chordDegree = 3, fkTom = 13, tonic = "D#", major = true),
    Chord(chordName = "Fm7", chordDegree = 4, fkTom = 13, tonic = "F", major = false),
    Chord(chordName = "Gm7", chordDegree = 5, fkTom = 13, tonic = "G", major = false),
    Chord(chordName = "Ab7M", chordDegree = 6, fkTom = 13, tonic = "G#", major = true),
    Chord(chordName = "Bb7", chordDegree = 7, fkTom = 13, tonic = "A#", major = true),


    //C#m
    Chord(chordName = "C#m7", chordDegree = 1, fkTom = 14, tonic = "C#", major = false),
    Chord(chordName = "D#m7(b5)", chordDegree = 2, fkTom = 14, tonic = "D#", major = false),
    Chord(chordName = "E7M", chordDegree = 3, fkTom = 14, tonic = "E", major = true),
    Chord(chordName = "F#m7", chordDegree = 4, fkTom = 14, tonic = "F#", major = false),
    Chord(chordName = "G#m7", chordDegree = 5, fkTom = 14, tonic = "G#", major = false),
    Chord(chordName = "A7M", chordDegree = 6, fkTom = 14, tonic = "A", major = true),
    Chord(chordName = "B7", chordDegree = 7, fkTom = 14, tonic = "B", major = true),

    )
