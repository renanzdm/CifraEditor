package com.renan.cifraeditor.domain.repository

import com.renan.cifraeditor.domain.entities.CipherEntity
import com.renan.cifraeditor.domain.entities.TomEntity
import com.renan.cifraeditor.domain.entities.WordsEntity
import com.renan.cifraeditor.utils.Resource

interface LocalRepository {
    suspend fun getAllToms(): Resource<List<TomEntity>>
    suspend fun getAllCiphers(): Resource<List<CipherEntity>>
    suspend fun saveCipher(cipherEntity: CipherEntity):Resource<Long>
    suspend fun saveWords(words:List<WordsEntity>):Resource<String>
    suspend fun getCipherById(id:Long):Resource<CipherEntity>


}