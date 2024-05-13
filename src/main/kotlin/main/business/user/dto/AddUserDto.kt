package main.business.user.dto

import java.time.LocalDate

data class AddUserDto(
    var userName: String? = null,
    var userEmail: String? = null,
    var accountId: Long? = null,
    var created: LocalDate? = null,
    var updated: LocalDate? = null
)
