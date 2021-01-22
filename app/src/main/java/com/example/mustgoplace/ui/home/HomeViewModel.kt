package com.example.mustgoplace.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mustgoplace.model.Event
import java.text.SimpleDateFormat
import java.util.*
import com.example.mustgoplace.util.getMonth
import javax.inject.Inject


class HomeViewModel @Inject constructor() : ViewModel() {

    private val _showToast = MutableLiveData<Event<String>>()
    val showToast: LiveData<Event<String>>
        get() = _showToast

    private val _navigateToRecord = MutableLiveData<Event<Unit>>()
    val navigateToRecord: LiveData<Event<Unit>>
        get() = _navigateToRecord

    private val _navigateToSetting = MutableLiveData<Event<Unit>>()
    val navigateToSetting: LiveData<Event<Unit>>
        get() = _navigateToSetting

    private val _appKillProcess = MutableLiveData<Event<Unit>>()
    val appkillProcess: LiveData<Event<Unit>>
        get() = _appKillProcess

    private val currentDate = MediatorLiveData<Date>().apply { value = Calendar.getInstance().time }

    private val _year = MediatorLiveData<String>()
    val year: LiveData<String>
        get() = _year

    private val _month = MediatorLiveData<String>()
    val month: LiveData<String>
        get() = _month

    private var pressedTime: Long = 0


    init {
        _year.addSource(currentDate) {
            _year.value = it.mapToYear()
        }

        _month.addSource(currentDate) {
            _month.value = it.mapToMonth()
        }
    }


    fun clickRecord() {
        _navigateToRecord.value = Event(Unit)
    }

    fun clickSetting() {
        _navigateToSetting.value = Event(Unit)
    }

    fun backPressed() {
        when (pressedTime) {
            0.toLong() -> {
                _showToast.postValue(Event("한 번 더 누르면 종료됩니다 :)"))
                pressedTime = System.currentTimeMillis()
            }

            else -> {
                val secondsTime = (System.currentTimeMillis() - pressedTime).toInt()

                if (secondsTime > 2000) {
                    _showToast.postValue(Event("한 번 더 누르면 종료됩니다 :)"))
                    pressedTime = 0
                } else {
                    _appKillProcess.value = Event(Unit)
                }
            }
        }
    }


    private fun Date.mapToYear(): String {
        val yearFormat = SimpleDateFormat("yyyy", Locale.KOREA)

        return yearFormat.format(this)
    }

    private fun Date.mapToMonth(): String {
        val monthFormat = SimpleDateFormat("MM", Locale.KOREA)
        val month = monthFormat.format(this)

        return getMonth(month)
    }

}