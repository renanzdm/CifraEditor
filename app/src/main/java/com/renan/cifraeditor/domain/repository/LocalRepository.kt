package com.renan.cifraeditor.domain.repository

import com.renan.cifraeditor.domain.entities.entitiesrelations.CipherWithWordsAndChords
import com.renan.cifraeditor.domain.entities.tables.Chord
import com.renan.cifraeditor.domain.entities.tables.Cipher
import com.renan.cifraeditor.domain.entities.tables.Tom
import com.renan.cifraeditor.domain.entities.tables.Word
import com.renan.cifraeditor.domain.entities.tables.WordChordCrossReference
import com.renan.cifraeditor.utils.Resource

interface LocalRepository {
    suspend fun getAllToms(): Resource<List<Tom>>
    suspend fun getAllCiphers(): Resource<List<Cipher>>
    suspend fun saveCipher(cipherEntity: Cipher): Resource<Long>
    suspend fun saveWords(words: List<Word>): Resource<String>
    suspend fun getCipherById(id: Long): Resource<CipherWithWordsAndChords>

    suspend fun initDatabaseDataInitial(): Resource<String>

    suspend fun getChordsByTom(id: Long): Resource<List<Chord>>
    suspend fun updateWord(word: Word): Resource<String>

    suspend fun updateWordChordCrossReference(entity:WordChordCrossReference): Resource<String>



}