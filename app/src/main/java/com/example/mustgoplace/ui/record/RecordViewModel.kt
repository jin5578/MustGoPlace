package com.example.mustgoplace.ui.record

import androidx.lifecycle.*
import com.example.mustgoplace.domain.PlaceRepository
import com.example.mustgoplace.model.Event
import com.example.mustgoplace.model.Place
import java.text.SimpleDateFormat
import java.util.*
import com.example.mustgoplace.util.getMonth
import com.example.mustgoplace.util.requireValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject


private typealias EVENT_DATE_PICKER = Triple<Int, Int, Int>

class RecordViewModel @Inject constructor(
    private val placeRepository: PlaceRepository
) : ViewModel() {

    private val _showToast = MutableLiveData<Event<String>>()
    val showToast: LiveData<Event<String>>
        get() = _showToast

    private val _showAlertDialog = MutableLiveData<Event<Unit>>()
    val showAlertDialog: LiveData<Event<Unit>>
        get() = _showAlertDialog

    private val _showDatePicker = MutableLiveData<Event<EVENT_DATE_PICKER>>()
    val showDatePicker: LiveData<Event<EVENT_DATE_PICKER>>
        get() = _showDatePicker

    private val _navigateToHome = MutableLiveData<Event<Unit>>()
    val navigateToHome: LiveData<Event<Unit>>
        get() = _navigateToHome

    private val _navigateToSearch = MutableLiveData<Event<Unit>>()
    val navigateToSearch: LiveData<Event<Unit>>
        get() = _navigateToSearch

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
        val contentIsNull = content.value.isNullOrEmpty()

        if (contentIsNull) {
            _showToast.postValue(Event("내용을 입력해주세요 :("))
        } else {
            onPlaceRecorded()
        }
    }

    fun onDayClicked() {
        _showDatePicker.value = Event(getDefaultDate())
    }

    fun onLocationClicked() {
        _navigateToSearch.value = Event(Unit)
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

    private fun onPlaceRecorded() {
        val place =
            Place(0, content.requireValue(), _date.requireValue().time, getCurrentDate().time)

        onPlaceInsert(place)
    }

    private fun onPlaceInsert(place: Place) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                placeRepository.insert(place)
            }

            _navigateToHome.value = Event(Unit)
        }
    }

    private fun getCurrentDate() = Calendar.getInstance()

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