package main.business.reminders.dto

data class ScheduleTaskRequestDto(val targetDateTime: String, val frequency: String)