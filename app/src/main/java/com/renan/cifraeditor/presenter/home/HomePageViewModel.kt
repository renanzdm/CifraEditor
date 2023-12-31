package com.renan.cifraeditor.presenter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.renan.cifraeditor.data.repository.LocalRepositoryImpl
import com.renan.cifraeditor.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(val localRepository: LocalRepositoryImpl) :
    ViewModel() {
    private val _state = MutableStateFlow(HomePageState())
    var state = _state.asStateFlow()




    fun initDataInitialDatabase(){
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
                            ciphers = result.data ?: emptyList(), loading = false
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


}