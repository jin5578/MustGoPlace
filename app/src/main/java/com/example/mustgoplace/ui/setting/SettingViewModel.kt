package com.example.mustgoplace.ui.setting

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mustgoplace.model.Event

class SettingViewModel @ViewModelInject constructor() : ViewModel() {

    private val _navigateToHome = MutableLiveData<Event<Unit>>()
    val navigateToHome: LiveData<Event<Unit>>
        get() = _navigateToHome


    fun clickPrevious() {
        _navigateToHome.value = Event(Unit)
    }

}