package com.example.mustgoplace.ui.record

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mustgoplace.model.Event
import java.text.SimpleDateFormat
import java.util.*
import com.example.mustgoplace.util.getMonth
import timber.log.Timber


private typealias EVENT_DATE_PICKER = Triple<Int, Int, Int>

class RecordViewModel @ViewModelInject constructor() : ViewModel() {

    private val _showAlertDialog = MutableLiveData<Event<Unit>>()
    val showAlertDialog: LiveData<Event<Unit>>
        get() = _showAlertDialog

    private val _showDatePicker = MutableLiveData<Event<EVENT_DATE_PICKER>>()
    val showDatePicker: LiveData<Event<EVENT_DATE_PICKER>>
        get() = _showDatePicker

    private val _navigateToHome = MutableLiveData<Event<Unit>>()
    val navigateToHome: LiveData<Event<Unit>>
        get() = _navigateToHome

    private val _date = MediatorLiveData<Calendar>().apply { value = Calendar.getInstance() }

    private val _monthYear = MediatorLiveData<String>()
    val monthYear: LiveData<String>
        get() = _monthYear

    private val _dayOfMonth = MediatorLiveData<String>()
    val dayOfMonth: LiveData<String>
        get() = _dayOfMonth

    private val _dayOfWeek = MediatorLiveData<String>()
    val dayOfWeek: LiveData<String>
        get() = _dayOfWeek

    private val _textAlign =
        MutableLiveData<Int>().apply { value = 0 }     // 0: left, 1: center, 2: right
    val textAlign: LiveData<Int>
        get() = _textAlign

    val content = MutableLiveData<String>()


    init {
        _monthYear.addSource(_date) {
            _monthYear.value = it.mapToMonthYearFormat()
        }

        _dayOfMonth.addSource(_date) {
            _dayOfMonth.value = it.mapToDayOfMonthFormat()
        }

        _dayOfWeek.addSource(_date) {
            _dayOfWeek.value = it.mapToDayOfWeekFormat()
        }
    }


    fun onPreviousClicked() {
        checkContentIsNull()
    }

    fun onConfirmClicked() {
        Timber.e("currentDate : ${_date.value}")
    }

    fun onDayClicked() {
        _showDatePicker.value = Event(getDefaultDate())
    }

    fun onTextAlignClicked() {
        changeTextAlign()
    }

    fun setDateValue(year: Int, month: Int, dayOfMonth: Int) {
        _date.value = update(year, month, dayOfMonth)
    }

    fun checkContentIsNull() {
        val contentIsNull = content.value.isNullOrEmpty()

        if (contentIsNull) {
            _navigateToHome.value = Event(Unit)
        } else {
            _showAlertDialog.value = Event(Unit)
        }
    }

    private fun getDefaultDate(): EVENT_DATE_PICKER {
        val calendar = _date.value ?: Calendar.getInstance()

        return Triple(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    private fun update(year: Int, month: Int, dayOfMonth: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.apply {
            set(year, month, dayOfMonth)
        }

        return calendar
    }

    private fun changeTextAlign() {
        when (_textAlign.value) {
            0 -> _textAlign.value = 1
            1 -> _textAlign.value = 2
            2 -> _textAlign.value = 0
        }
    }


    private fun Calendar.mapToMonthYearFormat(): String {
        val dateFormat = SimpleDateFormat("MM.yyyy", Locale.KOREA)
        val current = dateFormat.format(this.time)

        return getMonth(current.substring(0, 2)) + " " + current.substring(3, 7)
    }

    private fun Calendar.mapToDayOfMonthFormat(): String {
        val dateFormat = SimpleDateFormat("dd", Locale.KOREA)

        return dateFormat.format(this.time)
    }

    private fun Calendar.mapToDayOfWeekFormat(): String {
        val dateFormat = SimpleDateFormat("E", Locale.ENGLISH)

        return dateFormat.format(this.time)
    }

}