package com.renan.cifraeditor.domain.repository

import com.renan.cifraeditor.domain.entities.entitiesrelations.CipherWithWordsAndChords
import com.renan.cifraeditor.domain.entities.tables.Chord
import com.renan.cifraeditor.domain.entities.tables.Cipher
import com.renan.cifraeditor.domain.entities.tables.Tom
import com.renan.cifraeditor.domain.entities.tables.Word
import com.renan.cifraeditor.domain.entities.tables.WordChordCrossReference
import com.renan.cifraeditor.data.models.Resource
import com.renan.cifraeditor.domain.entities.entitiesrelations.WordWithChords

interface LocalRepository {
    suspend fun getAllToms(): Resource<List<Tom>>
    suspend fun getAllCiphers(): Resource<List<Cipher>>
    suspend fun getAllChords(): Resource<List<Chord>>
    suspend fun saveCipher(cipherEntity: Cipher): Resource<Long>
    suspend fun updateCipher(cipherEntity: Cipher): Resource<Unit>
    suspend fun saveWords(words: List<Word>): Resource<String>
    suspend fun getCipherById(id: Long): Resource<CipherWithWordsAndChords>
    suspend fun initDatabaseDataInitial(): Resource<String>
    suspend fun getChordsByTom(id: Long): Resource<List<Chord>>
    suspend fun updateWord(word: Word): Resource<String>
    suspend fun insertWordChordCrossReference(entity: WordChordCrossReference): Resource<String>
    suspend fun deleteWordChordCrossReference(entity: WordChordCrossReference): Resource<String>
    suspend fun updateWordChordCrossReference(entity: WordChordCrossReference): Resource<String>
    suspend fun getTomById(id:Long): Resource<Tom>
    suspend fun deleteCipher(cipher:Cipher,words: List<WordWithChords>): Resource<String>
    suspend fun deleteAllChordsByWords(words: List<WordWithChords>): Resource<String>

    suspend fun upsertWord(word: Word):Resource<String>


}