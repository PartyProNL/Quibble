package me.partypronl.quibble.util

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import java.util.Calendar
import kotlin.math.abs

object DateUtil {
    /**
     * Takes a date and adds one day
     * @param date The date to add one day too
     * @return The date with one day added, in milliseconds
     */
    fun getNextDay(date: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1)
        return calendar.timeInMillis
    }

    /**
     * Takes a date and takes one day off
     * @param date The date to take a day off
     * @return The date with one day removed, in milliseconds
     */
    fun getPreviousDay(date: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1)
        return calendar.timeInMillis
    }

    /**
     * Get the current date
     * Sets the date to the start of the day
     * @return Current date in milliseconds as a long
     */
    fun getToday(): Long {
        val todayInMillis = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis

        return todayInMillis
    }

    /**
     * Gets the difference in days between two dates
     * @param from The first date
     * @param to The second date
     * @return Difference in days as an integer, always positive
     */
    fun getDifferenceInDays(from: Long, to: Long): Int {
        val deltaDate = Calendar.getInstance()
        deltaDate.timeInMillis = from - to
        return abs(deltaDate.get(Calendar.DATE) - 1)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    val todayAndBeforeSelectableDates = object : SelectableDates {
        val today = getNextDay(getToday())

        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis < today
        }
    }
}