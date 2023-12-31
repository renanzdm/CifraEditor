package com.renan.cifraeditor.data.repository

import com.renan.cifraeditor.domain.daos.ChordDao
import com.renan.cifraeditor.domain.entities.tables.Cipher
import com.renan.cifraeditor.domain.daos.CipherDao
import com.renan.cifraeditor.domain.daos.TomDao
import com.renan.cifraeditor.domain.daos.WordChordCrossReferenceDao
import com.renan.cifraeditor.domain.daos.WordDao
import com.renan.cifraeditor.domain.database.chords
import com.renan.cifraeditor.domain.database.toms
import com.renan.cifraeditor.domain.entities.entitiesrelations.CipherWithWordsAndChords
import com.renan.cifraeditor.domain.entities.tables.Chord
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
    private val chordDao: ChordDao,
    private val wordChordCrossReferenceDao: WordChordCrossReferenceDao
) : LocalRepository {
    override suspend fun getAllToms(): Resource<List<Tom>> {
        return withContext(Dispatchers.IO) {
            try {
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
                val idsWords = wordDao.insertAllWords(words)
                if (idsWords.isNotEmpty()) {
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

    override suspend fun initDatabaseDataInitial(): Resource<String> {
        return withContext(Dispatchers.IO) {
            try {
                val tomsRes = tomDao.getAll()
                if (tomsRes.isEmpty()) {
                    for (tom in toms) {
                        tomDao.insert(tom = tom)
                    }
                }
                val chordsRes = chordDao.getAll()
                if (chordsRes.isEmpty()) {
                    for (chord in chords) {
                        chordDao.insert(chord = chord)
                    }
                }
                return@withContext Resource.Success("")
            } catch (e: Exception) {
                return@withContext Resource.Error(e.message ?: "")

            }
        }
    }

    override suspend fun getChordsByTom(id: Long): Resource<List<Chord>> {
        return withContext(Dispatchers.IO) {
            try {
                val chords = chordDao.getByTom(idTom = id)
                return@withContext Resource.Success(data = chords)
            } catch (ex: Exception) {
                return@withContext Resource.Error(message = ex.message ?: "")
            }
        }
    }

    override suspend fun updateWord(word: Word): Resource<String> {
        return withContext(Dispatchers.IO) {
            try {
                wordDao.updateWord(word = word)
                return@withContext Resource.Success(data ="Atualizado")
            } catch (ex: Exception) {
                return@withContext Resource.Error(message = ex.message ?: "")
            }
        }
    }

    override suspend fun updateWordChordCrossReference(entity: WordChordCrossReference): Resource<String> {
        return withContext(Dispatchers.IO) {
            try {
                wordChordCrossReferenceDao.insert(entity)
                return@withContext Resource.Success(data ="Sucesso")
            } catch (ex: Exception) {
                return@withContext Resource.Error(message = ex.message ?: "")
            }
        }
    }
}