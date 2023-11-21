package com.renan.cifraeditor.data.repository

import com.renan.cifraeditor.data.mappers.toCipher
import com.renan.cifraeditor.data.mappers.toEntity
import com.renan.cifraeditor.data.mappers.toWords
import com.renan.cifraeditor.domain.daos.CipherDao
import com.renan.cifraeditor.domain.entities.TomEntity
import com.renan.cifraeditor.domain.daos.TomDao
import com.renan.cifraeditor.domain.daos.WordDao
import com.renan.cifraeditor.domain.database.toms
import com.renan.cifraeditor.domain.entities.CipherEntity
import com.renan.cifraeditor.domain.entities.WordsEntity
import com.renan.cifraeditor.domain.repository.LocalRepository
import com.renan.cifraeditor.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val tomDao: TomDao,
    private val cipherDao: CipherDao,
    private val wordDao: WordDao,
) : LocalRepository {
    override suspend fun getAll(): Resource<List<TomEntity>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = tomDao.getAll()
                if (response.isEmpty()) {
                    for (tom in toms) {
                        tomDao.insert(tom = tom)
                    }
                }
                return@withContext Resource.Success(data = tomDao.getAll().toEntity())
            } catch (e: Exception) {
                return@withContext Resource.Error(message = e.localizedMessage ?: "")
            }
        }
    }

    override suspend fun saveCipher(cipherEntity: CipherEntity): Resource<Long> {
        return withContext(Dispatchers.IO) {
            try {
                val insertedId: Long = cipherDao.insert(cipherEntity.toCipher())
                return@withContext Resource.Success(insertedId)
            } catch (ex: Exception) {
                return@withContext Resource.Error(ex.message ?: "")
            }
        }
    }

    override suspend fun saveWords(words: List<WordsEntity>): Resource<String> {
        return withContext(Dispatchers.IO) {
            try {
                wordDao.insertAllWords(words.toWords())
                return@withContext Resource.Success("Inserido com Sucesso")
            } catch (ex: Exception) {
                return@withContext Resource.Error(ex.message ?: "")
            }
        }
    }
}