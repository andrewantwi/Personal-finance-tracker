package main.business.reminders.boundary

import jakarta.ws.rs.core.Response
import main.business.reminders.dto.EditReminderDto
import main.business.reminders.dto.SetRemindersDto

interface RemindersRouteInt {
    fun setReminder(setReminderDto: SetRemindersDto): Response

    fun getReminder(id: Long): Response

    fun deleteReminder(id: Long): Response

    fun editReminder(editReminderDto : EditReminderDto): Response

    fun getAllReminders(): Response
}