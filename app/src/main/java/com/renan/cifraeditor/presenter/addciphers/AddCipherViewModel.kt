package com.renan.cifraeditor.presenter.addciphers

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.renan.cifraeditor.data.repository.TomRepositoryImpl
import com.renan.cifraeditor.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCipherViewModel @Inject constructor(private val tomRepositoryImpl: TomRepositoryImpl) :ViewModel(){

    private val _state = MutableStateFlow(AddCipherState())
    var state = _state.asStateFlow()


    fun getAllToms() {
        viewModelScope.launch {
            _state.update {
                it.copy(loading = true)
            }
            when (val result = tomRepositoryImpl.getAll()) {
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

}