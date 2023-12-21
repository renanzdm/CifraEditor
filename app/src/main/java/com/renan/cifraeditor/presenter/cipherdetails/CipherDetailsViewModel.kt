package com.renan.cifraeditor.presenter.cipherdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.renan.cifraeditor.data.repository.LocalRepositoryImpl
import com.renan.cifraeditor.domain.entities.entitiesrelations.WordWithChords
import com.renan.cifraeditor.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CipherDetailsViewModel @Inject constructor(private val localRepositoryImpl: LocalRepositoryImpl) :
    ViewModel() {
    private val _state = MutableStateFlow(CipherDetailsState())
    var state = _state.asStateFlow()
    fun getCipherById(id: Long) {
        _state.update {
            it.copy(
                loading = true,
            )
        }
        viewModelScope.launch {
            when (val res = localRepositoryImpl.getCipherById(id)) {
                is Resource.Success -> {
                    val cipher = res.data!!
                    _state.update {
                        it.copy(
                            name = cipher.cipher.cipherName,
                            idCipher = cipher.cipher.cipherId,
                            artist = cipher.cipher.cipherArtist,
                            fkTom = cipher.cipher.fkTom,
                            words = cipher.words,
                            wordsFormatted = splitListWords(cipher.words)
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            errorMessage = res.message, loading = false
                        )
                    }
                }
            }
        }
    }

    private fun splitListWords(
        words: List<WordWithChords>, sizeSubList: Int = 5
    ): List<List<WordWithChords>> {
        val sublistas = mutableListOf<List<WordWithChords>>()
        for (i in words.indices step sizeSubList) {
            sublistas.add(words.subList(i, i + sizeSubList))
        }
        return sublistas
    }

}