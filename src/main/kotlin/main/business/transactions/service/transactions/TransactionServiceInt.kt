package main.business.transactions.service.transactions

import main.business.transactions.dto.AddTransactionDto
import main.business.transactions.dto.EditTransactionDto
import main.business.transactions.dto.SetBudgetForTransactionDto
import main.business.transactions.dto.SetCategoryForTransactionDto
import main.business.transactions.enums.TransactionType
import main.business.transactions.repo.Transaction

interface TransactionServiceInt {
    fun addTransaction(addTransactionDto: AddTransactionDto): Transaction

    fun getTransactionByID(id: Long): Transaction

    fun editTransaction(editTransactionDto: EditTransactionDto): Transaction

    fun deleteTransaction(id:Long): String

    fun getAllTransactions(): List<Transaction>

    fun getTransactionsByType(type : TransactionType) : List<Transaction>

    fun getTransactionsByBudget(budgetId : Long) : List<Transaction>

    fun setBudgetForTransaction(setBudgetForTransactionDto: SetBudgetForTransactionDto) : Transaction
    

    fun setCategoryForTransaction(setCategoryForTransactionDto: SetCategoryForTransactionDto)
}