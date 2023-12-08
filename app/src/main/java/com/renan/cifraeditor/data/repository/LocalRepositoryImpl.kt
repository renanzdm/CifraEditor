package com.renan.cifraeditor.data.repository

import com.renan.cifraeditor.domain.entities.tables.Cipher
import com.renan.cifraeditor.domain.daos.CipherDao
import com.renan.cifraeditor.domain.daos.TomDao
import com.renan.cifraeditor.domain.daos.WordDao
import com.renan.cifraeditor.domain.database.toms
import com.renan.cifraeditor.domain.entities.entitiesrelations.CipherWithWordsAndChords
import com.renan.cifraeditor.domain.entities.tables.Tom
import com.renan.cifraeditor.domain.entities.tables.Word
import com.renan.cifraeditor.domain.entities.tables.WordChordCrossReference
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
    override suspend fun getAllToms(): Resource<List<Tom>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = tomDao.getAll()
                if (response.isEmpty()) {
                    for (tom in toms) {
                        tomDao.insert(tom = tom)
                    }
                }
                return@withContext Resource.Success(data = tomDao.getAll())
            } catch (e: Exception) {
                return@withContext Resource.Error(message = e.localizedMessage ?: "")
            }
        }
    }

    override suspend fun getAllCiphers(): Resource<List<Cipher>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = cipherDao.getAll()
                return@withContext Resource.Success(data = response)
            } catch (e: Exception) {
                return@withContext Resource.Error(message = e.localizedMessage ?: "")
            }
        }
    }

    override suspend fun saveCipher(cipherEntity: Cipher): Resource<Long> {
        return withContext(Dispatchers.IO) {
            try {
                val insertedId: Long = cipherDao.insert(cipherEntity)
                return@withContext Resource.Success(insertedId)
            } catch (ex: Exception) {
                return@withContext Resource.Error(ex.message ?: "")
            }
        }
    }

    override suspend fun saveWords(words: List<Word>): Resource<String> {
        return withContext(Dispatchers.IO) {
            try {
               val idsWords =  wordDao.insertAllWords(words)
                if(idsWords.isNotEmpty()){
                    wordDao.insertAllWordsChords(idsWords.map { WordChordCrossReference(wordId = it) })
                }
                return@withContext Resource.Success("Inserido com Sucesso")
            } catch (ex: Exception) {
                return@withContext Resource.Error(ex.message ?: "")
            }
        }
    }

    override suspend fun getCipherById(id: Long): Resource<CipherWithWordsAndChords> {
        return withContext(Dispatchers.IO) {
            try {
                val cipher: CipherWithWordsAndChords = cipherDao.getById(id)
                return@withContext Resource.Success(cipher)
            } catch (ex: Exception) {
                return@withContext Resource.Error(ex.message ?: "")
            }
        }
    }
}