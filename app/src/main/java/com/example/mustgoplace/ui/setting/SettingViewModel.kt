package com.example.mustgoplace.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mustgoplace.model.Event
import javax.inject.Inject


class SettingViewModel @Inject constructor() : ViewModel() {

    private val _navigateToHome = MutableLiveData<Event<Unit>>()
    val navigateToHome: LiveData<Event<Unit>>
        get() = _navigateToHome


    fun clickPrevious() {
        _navigateToHome.value = Event(Unit)
    }

}