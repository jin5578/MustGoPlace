package com.example.mustgoplace.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mustgoplace.model.Event
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel @ViewModelInject constructor() : ViewModel() {

    private val currentTime = Calendar.getInstance().time

    private val _navigateToRecord = MutableLiveData<Event<Unit>>()
    val navigateToRecord: LiveData<Event<Unit>>
        get() = _navigateToRecord

    private val _navigateToSetting = MutableLiveData<Event<Unit>>()
    val navigateToSetting: LiveData<Event<Unit>>
        get() = _navigateToSetting

    private val _year = MutableLiveData<String>().apply { value = getYear() }
    val year: LiveData<String>
        get() = _year

    private val _month = MutableLiveData<String>().apply { value = getMonth() }
    val month: LiveData<String>
        get() = _month


    fun clickRecord() {
        _navigateToRecord.value = Event(Unit)
    }

    fun clickSetting() {
        _navigateToSetting.value = Event(Unit)
    }


    private fun getYear(): String {
        val yearFormat = SimpleDateFormat("yyyy", Locale.KOREA)

        return yearFormat.format(currentTime).toString()
    }

    private fun getMonth(): String {
        val monthFormat = SimpleDateFormat("MM", Locale.KOREA)

        return monthFormat.format(currentTime)
    }

}