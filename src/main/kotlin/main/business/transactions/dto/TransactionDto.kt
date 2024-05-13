package main.business.transactions.dto
import main.business.transactions.enums.TransactionType
import java.time.LocalDateTime

class TransactionDto {
    var id: Long? = null

    var userId: Long? = null

    var amount: Double? = null


    var description: String? = null


    var categoryId: Long? = null


    var date: LocalDateTime? = null


    var transactionType: TransactionType? = null


    var created: LocalDateTime? = null


    var updated: LocalDateTime? = null


    val version: Long = 0
}


