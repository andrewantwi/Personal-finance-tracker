package main.business.transactions.dto


import io.smallrye.common.constraint.NotNull
import main.business.transactions.enums.TransactionType


data class AddTransactionDto(

    @NotNull
    var userId: Long? = null,

    @NotNull
    var amount: Double? = null,

    var description: String? = null,

    var budgetId: Long? = null,

    var categoryId: Long?,


    var transactionType: TransactionType? = null,


    val version: Long
)
