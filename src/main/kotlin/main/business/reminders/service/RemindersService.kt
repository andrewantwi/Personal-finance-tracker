package main.business.reminders.service

import jakarta.inject.Inject
import jakarta.ws.rs.NotFoundException
import main.business.reminders.dto.EditReminderDto
import main.business.reminders.dto.SetRemindersDto
import main.business.reminders.repo.Reminder
import main.business.reminders.repo.RemindersRepo
import main.utils.Scheduler

class RemindersService : RemindersInt {

    @Inject
    private  lateinit var remindersRepo : RemindersRepo

    @Inject
    lateinit var scheduler: Scheduler


    override fun setReminder(setReminderDto: SetRemindersDto): Reminder {
        val reminder = Reminder()
        reminder.time = setReminderDto.time
        reminder.name = setReminderDto.name
        reminder.description = setReminderDto.description
        reminder.id = setReminderDto.id
        reminder.reminderType = setReminderDto.reminderType

        // Initialize the scheduler if not initialized yet
        if (!::scheduler.isInitialized) {
            scheduler = Scheduler()
        }

        // Parse the target date and time
//        val dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        val targetDateTimeStr = setReminderDto.time // Assuming it's in the format "dd/MM/yyyy HH:mm:ss"
        val frequency = setReminderDto.reminderType

        val task = Runnable {
            // This is the task that will be executed when the reminder fires
            println("Reminder: ${reminder.name} - ${reminder.description}")
        }

        // Schedule the task
//        scheduler.scheduleTask(targetDateTimeStr, frequency, task)

        // Persist the reminder
        remindersRepo.persist(reminder)

        return reminder
    }

    override fun getReminder(id: Long): Reminder {
        try {
            return remindersRepo.findById(id) ?: throw NotFoundException("Reminder not found with ID: $id")
        } catch (e: Exception) {
            throw RuntimeException("Failed to retrieve Reminder by ID::-> $id - ${e.message}", e)
        }
    }

    override fun deleteReminder(id: Long): String {
        try {
            val transaction = getReminder(id)
            remindersRepo.delete(transaction)
            return "Deleted successfully"
        } catch (e: Exception) {
            throw RuntimeException("Failed to delete transaction with ID: $id - ${e.message}", e)
        }
    }

    override fun editReminder(editReminderDto: EditReminderDto): Reminder {
        try {
            val reminder = getReminder(editReminderDto.id!!)
            reminder.time = editReminderDto.time
            reminder.name = editReminderDto.name
            reminder.description = editReminderDto.description
            reminder.id = editReminderDto.id
            remindersRepo.persistAndFlush(reminder)

            return reminder
        } catch (e: Exception) {
            throw RuntimeException("Failed to edit Reminder: ${e.message}", e)
        }
    }

    override fun getAllReminders(): List<Reminder> {
        try {
            return remindersRepo.listAll()
        } catch (e: Exception) {
            throw RuntimeException("Failed to retrieve all reminders: ${e.message}", e)
        }
    }
}