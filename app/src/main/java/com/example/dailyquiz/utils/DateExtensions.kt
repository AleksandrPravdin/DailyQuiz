package com.example.dailyquiz.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toDayMonthString(): String {
    return SimpleDateFormat("d MMMM", Locale.getDefault())
        .format(Date(this))
}

fun Long.toTimeString(): String {
    return SimpleDateFormat("HH:mm", Locale.getDefault())
        .format(Date(this))
}