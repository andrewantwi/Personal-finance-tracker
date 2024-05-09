package main.business.user.dto

import java.time.LocalDate

class AddUserDto {
    var userName: String = ""
    var userEmail: String = ""
    var accountId : Long? = null
    var created : LocalDate? = null
    var updated : LocalDate? = null
}