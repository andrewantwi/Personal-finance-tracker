package main.business.reminders.service

import main.business.reminders.dto.SetRemindersDto
import main.business.reminders.repo.Reminders

interface RemindersInt {
    fun setReminder(setReminderDto: SetRemindersDto) : Reminders

    fun getReminder(id: Long): Reminders
}