package com.example.mustgoplace.ui.record

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*
import com.example.mustgoplace.util.getMonth


class RecordViewModel @ViewModelInject constructor() : ViewModel() {

    private val _textAlign = MutableLiveData<Int>().apply { value = 0 }     // 0: left, 1: center, 2: right
    val textAlign: LiveData<Int>
        get() = _textAlign

    private val currentDate = MediatorLiveData<Date>().apply { value = Calendar.getInstance().time }

    private val _monthYear = MediatorLiveData<String>()
    val monthYear: LiveData<String>
        get() = _monthYear


    init {
        _monthYear.addSource(currentDate) {
            _monthYear.value = it.mapToMonthYearFormat()
        }
    }


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

    private fun Date.mapToMonthYearFormat(): String {
        val dateFormat = SimpleDateFormat("MM.yyyy", Locale.KOREA)
        val current = dateFormat.format(this)

        return getMonth(current.substring(0, 2)) + " " + current.substring(3, 7)
    }

}