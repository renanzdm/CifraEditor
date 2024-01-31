package com.renan.cifraeditor.domain.entities.entitiesrelations

import androidx.room.Embedded
import androidx.room.Relation
import com.renan.cifraeditor.domain.entities.tables.Cipher
import com.renan.cifraeditor.domain.entities.tables.Word

data class CipherWithWordsAndChords(
@Embedded val cipher: Cipher,
 @Relation(
     parentColumn = "cipherId",
     entityColumn = "fkChiper",
     entity = Word::class)
   val wordWithChords:List<WordWithChords>
)
