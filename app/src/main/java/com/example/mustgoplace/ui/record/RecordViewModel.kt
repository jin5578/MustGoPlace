package com.example.mustgoplace.ui.record

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecordViewModel @ViewModelInject constructor() : ViewModel() {

    private val _textAlign = MutableLiveData<Int>().apply { value = 0 }     // 0: left, 1: center, 2: right
    val textAlign: LiveData<Int>
        get() = _textAlign

    fun clickTextAlign() {
        changeTextAlign()
    }

    fun clickImage() {

    }

    private fun changeTextAlign() {
        when(_textAlign.value) {
            0 -> _textAlign.value = 1
            1 -> _textAlign.value = 2
            2 -> _textAlign.value = 0
        }
    }
}