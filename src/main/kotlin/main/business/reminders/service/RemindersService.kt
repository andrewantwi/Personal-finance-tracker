package main.business.reminders.service

import jakarta.inject.Inject
import jakarta.ws.rs.NotFoundException
import main.business.reminders.dto.SetRemindersDto
import main.business.reminders.repo.Reminders
import main.business.reminders.repo.RemindersRepo

class RemindersService : RemindersInt {

    @Inject
    private  lateinit var remindersRepo : RemindersRepo

    override fun setReminder(setReminderDto: SetRemindersDto): Reminders {
        val reminder = Reminders()
        reminder.time = setReminderDto.time
        reminder.name = setReminderDto.name
        reminder.description = setReminderDto.description
        reminder.id = setReminderDto.id
        return reminder

    }

    override fun getReminder(id: Long): Reminders {
        try {
            return remindersRepo.findById(id) ?: throw NotFoundException("Reminder not found with ID: $id")
        } catch (e: Exception) {
            throw RuntimeException("Failed to retrieve Reminder by ID::-> $id - ${e.message}", e)
        }
    }
}