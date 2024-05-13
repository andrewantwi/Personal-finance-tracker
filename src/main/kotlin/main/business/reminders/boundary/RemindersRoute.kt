package main.business.reminders.boundary

import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import main.business.reminders.dto.*
import main.business.reminders.service.RemindersInt
import main.utils.Scheduler
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.util.stream.Collectors

class RemindersRoute :RemindersRouteInt {
    @Inject
    private lateinit var remindersInt: RemindersInt
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    private val modelMapper = ModelMapper()

    @Inject
    lateinit var taskScheduler: Scheduler


    @POST
    override fun setReminder(setReminderDto: SetRemindersDto): Response {
        try {
            remindersInt.setReminder(setReminderDto)
            return Response.ok().build()
        } catch (e: Exception) {
            return Response.serverError().entity("Failed to set budget for transaction: ${e.message}").build()
        }
    }

    @POST
    @Path("/schedule-task")
    @Consumes(MediaType.APPLICATION_JSON)
    fun scheduleTask(request: ScheduleTaskRequestDto): Response {
        try {
            val task = Runnable {
                // Implement your task logic here
                println("Task executed at: ${LocalDateTime.now()}")
            }
            taskScheduler.scheduleTask(request.targetDateTime, request.frequency, task)
            return Response.ok().build()
        } catch (e: Exception) {
            return Response.serverError().entity("Failed to schedule task: ${e.message}").build()
        }
    }

    @GET
    @Path("/{id}")
    override fun getReminder(@PathParam("id") id: Long): Response {
        val reminder = remindersInt.getReminder(id)
        val reminderDto = modelMapper.map(reminder, ReminderDto::class.java)

        return Response.ok(reminderDto).build()
    }

    @DELETE
    @Path("/{id}")
    override fun deleteReminder(@PathParam("id") id: Long): Response {
        val deletedReminder = remindersInt.deleteReminder(id)

        return Response.ok(deletedReminder).build()
    }

    override fun editReminder(editReminderDto: EditReminderDto): Response {
        try {
            remindersInt.editReminder(editReminderDto)
            return Response.ok().build()
        } catch (e: Exception) {
            return Response.serverError().entity("Failed to edit budget for transaction: ${e.message}").build()
        }
    }

    override fun getAllReminders(): Response {
        logger.info("Request to get all Transactions")
        val reminders = remindersInt.getAllReminders()

        val dtos = reminders
            .stream()
            .map { modelMapper.map(it, ReminderDto::class.java) }
            .collect(Collectors.toList())
        logger.info("response for get All Reminders {}", dtos)

        return Response.ok(dtos).build()
    }
}