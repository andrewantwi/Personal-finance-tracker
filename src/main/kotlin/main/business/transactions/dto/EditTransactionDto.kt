package main.business.transactions.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import main.business.transactions.enums.TransactionType
import main.business.categories.repo.Category
import java.time.LocalDateTime

class EditTransactionDto {

        @NotBlank
        var id: Long? = null

        @NotBlank
        var userId: Long? = null

        @NotNull
        var amount: Double? = null


        var description: String? = null


        var category: Category? = null


        var date: LocalDateTime? = null


        var transactionType: TransactionType? = null


        var created: LocalDateTime? = null


        var updated: LocalDateTime? = null


        val version: Long = 0


}