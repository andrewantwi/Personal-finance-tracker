package main.business.reminders.dto

import io.smallrye.common.constraint.NotNull
import java.util.*

class ReminderDto {

    @NotNull
    var id: Long? = null

    @NotNull
    val name : String? = null

    @NotNull
    val time : Date? = null

    @NotNull
    val description : String? = null
}