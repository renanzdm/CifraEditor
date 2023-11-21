package com.renan.cifraeditor.presenter.addciphers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.renan.cifraeditor.data.repository.LocalRepositoryImpl
import com.renan.cifraeditor.domain.entities.CipherEntity
import com.renan.cifraeditor.domain.entities.WordsEntity
import com.renan.cifraeditor.utils.Resource
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


    fun getAllToms() {
        viewModelScope.launch {
            _state.update {
                it.copy(loading = true)
            }
            when (val result = localRepositoryImpl.getAll()) {
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

    fun validateNameMusic(text: String): Boolean {
        if (text.isEmpty()) return true
        return false
    }


    fun setWords(letter: String, idCipher: Long) {
        val listWords: List<String> = letter.split(" ")
        _state.update {
            it.copy(words = listWords.map { word ->
                WordsEntity(name = word, fkChiper = idCipher)
            })
        }
    }

}