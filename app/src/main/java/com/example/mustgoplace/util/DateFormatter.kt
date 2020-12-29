package com.example.mustgoplace.util


fun getMonth(month: String): String {
    return when (month) {
        "01" -> "JANUARY"
        "02" -> "FEBRUARY"
        "03" -> "MARCH"
        "04" -> "APRIL"
        "05" -> "MAY"
        "06" -> "JUNE"
        "07" -> "JULY"
        "08" -> "AUGUST"
        "09" -> "SEPTEMBER"
        "10" -> "OCTOBER"
        "11" -> "NOVEMBER"
        else -> "DECEMBER"
    }
}