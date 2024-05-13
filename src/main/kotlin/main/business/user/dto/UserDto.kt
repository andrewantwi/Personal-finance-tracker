package main.business.user.dto

class UserDto(
    var id: Long? = null,
    var userName: String? = null,
    var userEmail: String? = null,
    var accountId: Long? = null,
    var amountSpent: Long? = null,
    var amountReceived: Long? = null,
    var amountSaved: Long? = null,
    var totalAmount: Long? = null,
)