package main.business.user.dto

import main.business.transactions.enums.TransactionType

class UpdateUserAmountDto {
   val  amount: Long = 0
   val type : TransactionType? = null
    val userId : Long? = null
}