package com.shtek7777.myfirstapplication.fragments

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import android.os.Build
import androidx.annotation.RequiresApi

fun calendarBirthday(calendar: Calendar): Long {

    val calendarNotify = calendar as GregorianCalendar
    var currentYear = Calendar.getInstance().get(Calendar.YEAR)
    var currentDay = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)

    calendarNotify[Calendar.DATE] = calendar[Calendar.DATE]
    calendarNotify[Calendar.MONTH] = calendar[Calendar.MONTH]
    calendarNotify[Calendar.YEAR] = calendar[Calendar.YEAR]
    calendarNotify[Calendar.HOUR] = 0
    calendarNotify[Calendar.MINUTE] = 0
    calendarNotify[Calendar.SECOND] = 0

    var calendarDay = calendar.get(Calendar.DAY_OF_YEAR)

    if (isFebruary29(calendar)) {
        calendarDay -= 1
    }

    if (calendar.isLeapYear(currentYear) || isFebruary29(calendar)) {
        currentDay -= 1
    }

    if (currentDay >= calendarDay) ++currentYear
    if (!calendar.isLeapYear(currentYear) && !calendar.isLeapYear(currentYear + 4) && isFebruary29(calendar)) {
        calendarNotify[Calendar.DATE] = 29
        calendarNotify[Calendar.YEAR] = currentYear + 4
    }

    return calendarNotify.timeInMillis
}

fun isFebruary29(calendar: Calendar) = (calendar.get(Calendar.MONTH) == Calendar.FEBRUARY
        && calendar.get(Calendar.DATE) == 29)