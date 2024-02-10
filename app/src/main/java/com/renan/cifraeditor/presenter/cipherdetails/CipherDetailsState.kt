package com.renan.cifraeditor.presenter.cipherdetails

import com.renan.cifraeditor.domain.entities.entitiesrelations.WordWithChords
import com.renan.cifraeditor.domain.entities.tables.Chord
import com.renan.cifraeditor.domain.entities.tables.Cipher
import com.renan.cifraeditor.domain.entities.tables.Tom

data class CipherDetailsState(
    var loading: Boolean = false,
    var errorMessage: String? = null,
    var cipher: Cipher? = null,
    var wordsFormatted: List<List<WordWithChords>> = emptyList(),
    var wordsWithChords: List<WordWithChords> = emptyList(),
    var chords: List<Chord> = emptyList(),
    var listToms: List<Tom> = emptyList(),
    var tomOfCipher: Tom? = null,
    var fontSize:Int=12
)