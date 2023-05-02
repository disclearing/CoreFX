package dev.cougar.core.util.duration

import java.util.regex.Pattern

class Duration(val value: Long) {
    var isPermanent = false

    companion object {
        fun fromString(source: String): Duration {
            if (source.equals("perm", ignoreCase = true) || source.equals("permanent", ignoreCase = true)) {
                val duration = Duration(Long.MAX_VALUE)
                duration.isPermanent = true
                return duration
            }
            var totalTime = 0L
            var found = false
            val matcher = Pattern.compile("\\d+\\D+").matcher(source)
            while (matcher.find()) {
                val s = matcher.group()
                val value = s.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)".toRegex()).toTypedArray()[0].toLong()
                when (s.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)".toRegex()).toTypedArray()[1]) {
                    "s" -> {
                        totalTime += value
                        found = true
                    }
                    "m" -> {
                        totalTime += value * 60
                        found = true
                    }
                    "h" -> {
                        totalTime += value * 60 * 60
                        found = true
                    }
                    "d" -> {
                        totalTime += value * 60 * 60 * 24
                        found = true
                    }
                    "w" -> {
                        totalTime += value * 60 * 60 * 24 * 7
                        found = true
                    }
                    "M" -> {
                        totalTime += value * 60 * 60 * 24 * 30
                        found = true
                    }
                    "y" -> {
                        totalTime += value * 60 * 60 * 24 * 365
                        found = true
                    }
                }
            }
            return Duration(if (!found) -1 else totalTime * 1000)
        }
    }
}