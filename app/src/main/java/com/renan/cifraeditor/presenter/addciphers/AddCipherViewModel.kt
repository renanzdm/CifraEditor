package com.renan.cifraeditor.presenter.addciphers

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.renan.cifraeditor.data.repository.LocalRepositoryImpl
import com.renan.cifraeditor.domain.entities.CipherEntity
import com.renan.cifraeditor.domain.entities.WordsEntity
import com.renan.cifraeditor.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCipherViewModel @Inject constructor(private val localRepositoryImpl: LocalRepositoryImpl) :
    ViewModel() {

    private val _state = MutableStateFlow(AddCipherState())
    var state = _state.asStateFlow()


    fun getAllToms() {
        viewModelScope.launch {
            _state.update {
                it.copy(loading = true)
            }
            when (val result = localRepositoryImpl.getAllToms()) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(loading = false, allToms = result.data.orEmpty())
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(loading = false, errorMessage = result.message)
                    }
                }
            }
        }
    }

    fun validateNameMusic(): Boolean {
        return _state.value.nameMusic?.isEmpty() ?: false
    }

    fun setNameMusic(name: String) {
        _state.update {
            it.copy(nameMusic = name)
        }
    }

    fun setNameArtist(name: String) {
        _state.update {
            it.copy(nameArtist = name)
        }
    }

    fun setTom(idTom: Long) {
        _state.update {
            it.copy(idTom = idTom)
        }
    }

    fun setLetterMusic(text: String) {
        _state.update {
            it.copy(letterMusic = text)
        }
    }


    private fun setWords(letter: String, idCipher: Long): List<WordsEntity> {
        val anySpacesRemoved = letter.trimIndent().replace(Regex("\\s+"), " ")
        val listWords: List<String> = anySpacesRemoved.split(" ")
        return listWords.map { word ->
            WordsEntity(name = word, fkChiper = idCipher)
        }
    }

    fun saveCipher(): Job {
        return viewModelScope.launch {
            val cipher = CipherEntity(
                name = _state.value.nameMusic!!,
                artist = _state.value.nameArtist,
                fkTom = _state.value.idTom
            )

            when (val result: Resource<Long> = localRepositoryImpl.saveCipher(cipher)) {
                is Resource.Success -> {
                    if (_state.value.letterMusic != null) {
                        val words =
                            setWords(idCipher = result.data!!, letter = _state.value.letterMusic!!)
                        val resultSaveWords: Resource<String> = localRepositoryImpl.saveWords(words)
                        Log.e("SUCESSO", resultSaveWords.data ?: "")
                        _state.update {
                            it.copy(
                                idCipherCreated = result.data
                            )
                        }
                    }
                }

                is Resource.Error -> {
                    Log.e("ERRO AO SALVAR CIFRA", result.message ?: "")
                    _state.update {
                        it.copy(
                            idCipherCreated = null
                        )
                    }
                }
            }
        }
    }

}