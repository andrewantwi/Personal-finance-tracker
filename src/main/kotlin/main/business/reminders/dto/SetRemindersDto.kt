package main.business.reminders.dto

import io.smallrye.common.constraint.NotNull
import main.business.reminders.enums.ReminderType
import java.util.*

class SetRemindersDto {

    @NotNull
    var id: Long? = null


    @NotNull
    val name : String? = null

    @NotNull
    val time : Date? = null

    @NotNull
    val description : String? = null

    @NotNull
    val reminderType : ReminderType? = null
}