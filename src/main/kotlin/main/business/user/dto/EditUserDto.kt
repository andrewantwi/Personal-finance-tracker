package main.business.user.dto

data class EditUserDto(
    var id: Long? = null,
    var userName: String? = null,
    var userEmail: String? = null,
    var accountId: Long? = null,
)
