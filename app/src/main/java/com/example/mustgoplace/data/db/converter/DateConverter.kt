package com.example.mustgoplace.data.db.converter

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
class DateConverter {

    companion object {
        private const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
    }

    private val format by lazy { SimpleDateFormat(DATE_FORMAT) }

    @TypeConverter
    fun dateToString(value: Date?): String? {
        return value?.let { format.format(it) }
    }

    @TypeConverter
    fun stringToDate(value: String?): Date? {
        return value?.let { format.parse(it) }
    }
}