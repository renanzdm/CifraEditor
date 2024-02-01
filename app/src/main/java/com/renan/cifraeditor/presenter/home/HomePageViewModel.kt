package com.renan.cifraeditor.presenter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.renan.cifraeditor.data.models.Resource
import com.renan.cifraeditor.data.repository.LocalRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(private val localRepository: LocalRepositoryImpl) :
    ViewModel() {
    private val _state = MutableStateFlow(HomePageState())
    var state = _state.asStateFlow()


    fun initDataInitialDatabase() {
        viewModelScope.launch {
            localRepository.initDatabaseDataInitial()
        }
    }


    fun getCiphers() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    loading = true
                )
            }
            when (val result = localRepository.getAllCiphers()) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            ciphers = result.data ?: emptyList(),
                            loading = false,
                            ciphersOriginal = result.data ?: emptyList(),
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            errorMessage = result.message, loading = false
                        )
                    }
                }
            }
        }
    }


    fun searchCipher(text: String) {
        val listSearched = _state.value.ciphersOriginal
        if (text.isNotEmpty()) {
            _state.update { state ->
                state.copy(ciphers = listSearched.filter {
                    it.cipherName.contains(text) || it.cipherArtist?.contains(
                        text
                    ) == true
                })
            }
        } else {
            _state.update { state ->
                state.copy(ciphers = _state.value.ciphersOriginal)
            }

        }


    }


}