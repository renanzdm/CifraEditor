package com.renan.cifraeditor.presenter.cipherDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.renan.cifraeditor.data.repository.LocalRepositoryImpl
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
                            name = cipher.name,
                            idCipher = cipher.id,
                            artist = cipher.artist,
                            fkTom = cipher.fkTom
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

}