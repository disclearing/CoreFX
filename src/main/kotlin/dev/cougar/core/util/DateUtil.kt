package dev.cougar.core.util

import java.util.*
import kotlin.math.abs

object DateUtil {
    private const val MAX_YEARS = 100000
    private fun dateDiff(type: Int, fromDate: Calendar, toDate: Calendar, future: Boolean): Int {
        val year = Calendar.YEAR
        val fromYear = fromDate[year]
        val toYear = toDate[year]
        if (abs(fromYear - toYear) > MAX_YEARS) {
            toDate[year] = fromYear +
                    if (future) MAX_YEARS else -MAX_YEARS
        }
        var diff = 0
        var savedDate = fromDate.timeInMillis
        while (future && !fromDate.after(toDate) || !future && !fromDate.before(toDate)) {
            savedDate = fromDate.timeInMillis
            fromDate.add(type, if (future) 1 else -1)
            diff++
        }
        diff--
        fromDate.timeInMillis = savedDate
        return diff
    }

    @JvmStatic
    fun formatDateDiff(date: Long): String {
        val c: Calendar = GregorianCalendar()
        c.timeInMillis = date
        val now: Calendar = GregorianCalendar()
        return formatDateDiff(now, c)
    }

    private fun formatDateDiff(fromDate: Calendar, toDate: Calendar): String {
        var future = false
        if (toDate == fromDate) {
            return "now"
        }
        if (toDate.after(fromDate)) {
            future = true
        }
        val sb = StringBuilder()
        val types = intArrayOf(
            Calendar.YEAR,
            Calendar.MONTH,
            Calendar.DAY_OF_MONTH,
            Calendar.HOUR_OF_DAY,
            Calendar.MINUTE,
            Calendar.SECOND
        )
        val names = arrayOf(
            "year",
            "years",
            "month",
            "months",
            "day",
            "days",
            "hour",
            "hours",
            "minute",
            "minutes",
            "second",
            "seconds"
        )
        var accuracy = 0
        for (i in types.indices) {
            if (accuracy > 2) {
                break
            }
            val diff = dateDiff(types[i], fromDate, toDate, future)
            if (diff > 0) {
                accuracy++
                sb.append(" ").append(diff).append(" ").append(names[i * 2 + (if (diff > 1) 1 else 0)])
            }
        }
        return if (sb.isEmpty()) {
            "now"
        } else sb.toString().trim { it <= ' ' }
    }
}