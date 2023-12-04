package com.renan.cifraeditor.data.mappers

import com.renan.cifraeditor.data.tables.Word
import com.renan.cifraeditor.domain.entities.WordsEntity

fun List<WordsEntity>.toWords(): List<Word> {
    val words = mutableListOf<Word>()
    forEach {
        words.add(
            Word(
                name = it.name, fkChiper = it.fkChiper, fkChord = it.fkChord, id = it.id
            )
        )
    }
    return words
}