package com.renan.cifraeditor.domain.entities.entitiesrelations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.renan.cifraeditor.domain.entities.tables.Chord
import com.renan.cifraeditor.domain.entities.tables.WordChordCrossReference
import com.renan.cifraeditor.domain.entities.tables.Word
data class WordWithChords(
    @Embedded val word:Word,
    @Relation(
        parentColumn = "wordId",
        entityColumn = "chordId",
        associateBy = Junction(WordChordCrossReference::class),
    )
    val chords:List<Chord>?
)
