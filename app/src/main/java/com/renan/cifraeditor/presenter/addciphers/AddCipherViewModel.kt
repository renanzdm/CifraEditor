package com.renan.cifraeditor.presenter.addciphers

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddCipherViewModel @Inject constructor() :ViewModel(){

    private val _state = MutableStateFlow(AddCipherState())
    var state = _state.asStateFlow()

}