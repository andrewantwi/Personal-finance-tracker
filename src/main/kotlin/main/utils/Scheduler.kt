package main.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class Scheduler {
    fun scheduleTask(targetDateTimeStr: String, frequency: String, task: Runnable) {
        val dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        val targetDateTime = LocalDateTime.parse(targetDateTimeStr, dateTimeFormat)
        val currentDateTime = LocalDateTime.now()

        val scheduler = Executors.newScheduledThreadPool(1)

        val delay = calculateDelay(targetDateTime, frequency, currentDateTime)
        scheduler.scheduleAtFixedRate(task, delay, calculatePeriod(frequency), TimeUnit.SECONDS)
    }

    private fun calculateDelay(targetDateTime: LocalDateTime, frequency: String, currentDateTime: LocalDateTime): Long {
        return when (frequency.lowercase()) {
            "monthly" -> {
                val nextMonth = currentDateTime.with(TemporalAdjusters.firstDayOfNextMonth()).withHour(targetDateTime.hour).withMinute(targetDateTime.minute).withSecond(targetDateTime.second)
                currentDateTime.until(nextMonth, ChronoUnit.SECONDS)
            }
            "daily" -> {
                val nextDay = currentDateTime.plusDays(1).withHour(targetDateTime.hour).withMinute(targetDateTime.minute).withSecond(targetDateTime.second)
                currentDateTime.until(nextDay, ChronoUnit.SECONDS)
            }
            else -> throw IllegalArgumentException("Invalid frequency: $frequency")
        }
    }

    private fun calculatePeriod(frequency: String): Long {
        return when (frequency.lowercase()) {
            "monthly" -> TimeUnit.DAYS.toSeconds(30) // Approximation, adjust as needed
            "daily" -> TimeUnit.DAYS.toSeconds(1)
            else -> throw IllegalArgumentException("Invalid frequency: $frequency")
        }
    }
}
