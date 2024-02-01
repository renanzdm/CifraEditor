package com.renan.cifraeditor.presenter.addciphers

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.renan.cifraeditor.data.repository.LocalRepositoryImpl
import com.renan.cifraeditor.domain.entities.tables.Cipher
import com.renan.cifraeditor.domain.entities.tables.Word
import com.renan.cifraeditor.data.models.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun validateNameMusic(): Boolean {
        return _state.value.nameMusic?.isEmpty() ?: true
    }

    fun validateLetterMusic(): Boolean {
        return _state.value.letterMusic?.isEmpty() ?: true
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


    private fun setWords(letter: String, idCipher: Long): List<Word> {
        val anySpacesRemoved = letter.trimIndent().replace(Regex("\\s+"), " ")
        val listWords: List<String> = anySpacesRemoved.split(" ")
        return listWords.map { word ->
            Word(wordName = word, fkChiper = idCipher)
        }
    }

    fun saveCipher() {
        viewModelScope.launch {
            _state.update {
                it.copy(loading = true)
            }


            val cipher = Cipher(
                cipherName = _state.value.nameMusic!!,
                cipherArtist = _state.value.nameArtist,
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
                                idCipherCreated = result.data, loading = false
                            )
                        }
                    }
                }

                is Resource.Error -> {
                    Log.e("ERRO AO SALVAR CIFRA", result.message ?: "")
                    _state.update {
                        it.copy(
                            idCipherCreated = null, loading = false
                        )
                    }
                }
            }
        }
    }

}