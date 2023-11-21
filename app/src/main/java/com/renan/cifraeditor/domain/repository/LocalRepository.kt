package com.renan.cifraeditor.domain.repository

import com.renan.cifraeditor.domain.entities.CipherEntity
import com.renan.cifraeditor.domain.entities.TomEntity
import com.renan.cifraeditor.domain.entities.WordsEntity
import com.renan.cifraeditor.utils.Resource

interface LocalRepository {
    suspend fun getAll(): Resource<List<TomEntity>>
    suspend fun saveCipher(cipherEntity: CipherEntity):Resource<Long>
    suspend fun saveWords(words:List<WordsEntity>):Resource<String>


}