package main.business.transactions.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import main.business.budgets.repo.Budget
import main.business.transactions.enums.TransactionType
import main.business.categories.repo.Category

import java.io.Serializable
import java.time.LocalDateTime

class AddTransactionDto: Serializable {
    @NotBlank
    var id: Long? = null

    @NotBlank
    var userId: Long? = null

    @NotNull
    var amount: Double? = null

    var description: String? = null

    var budget: Budget? = null

    var category: Category? = null


    var date: LocalDateTime? = null


    var transactionType: TransactionType? = null


    val version: Long = 0
}
