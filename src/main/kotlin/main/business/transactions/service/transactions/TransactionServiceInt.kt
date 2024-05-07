package main.business.transactions.service.transactions

import main.business.transactions.dto.CreateTransactionDto
import main.business.transactions.dto.EditTransactionDto
import main.business.transactions.enums.TransactionType
import main.business.transactions.repo.Transaction

interface TransactionServiceInt {
    fun createTransaction(createTransactionDto: CreateTransactionDto): Transaction

    fun getTransactionByID(id: Long): Transaction

    fun editTransaction(editTransactionDto: EditTransactionDto): Transaction

    fun deleteTransaction(id:Long): String

    fun getAllTransactions(): List<Transaction>

    fun getTransactionsByType(type : TransactionType) : List<Transaction>
}