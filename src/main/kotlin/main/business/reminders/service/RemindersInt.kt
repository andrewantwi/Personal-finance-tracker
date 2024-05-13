package main.business.reminders.service

import main.business.reminders.dto.EditReminderDto
import main.business.reminders.dto.SetRemindersDto
import main.business.reminders.repo.Reminder

interface RemindersInt {
    fun setReminder(setReminderDto: SetRemindersDto): Reminder

    fun getReminder(id: Long): Reminder

    fun deleteReminder(id: Long): String

    fun editReminder(editReminderDto : EditReminderDto): Reminder

    fun getAllReminders(): List<Reminder>
}