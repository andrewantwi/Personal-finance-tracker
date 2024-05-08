package main.business.transactions.boundary.http

import jakarta.ws.rs.PathParam
import jakarta.ws.rs.core.Response
import main.business.transactions.dto.AddTransactionDto
import main.business.transactions.dto.EditTransactionDto
import main.business.transactions.dto.SetBudgetForTransactionDto
import main.business.transactions.dto.SetCategoryForTransactionDto

interface TransactionsInt {
    fun addTransaction(addTransactionDto: AddTransactionDto): Response

    fun setBudgetForTransaction(setBudgetForTransactionDto: SetBudgetForTransactionDto) : Response
    fun setCategoryForTransaction(setCategoryForTransactionDto: SetCategoryForTransactionDto): Response
    fun getTransaction(id:Long): Response

    fun editTransaction(editTransactionDto: EditTransactionDto): Response

    fun deleteTransaction(id : Long): Response

    fun getAllTransactions(): Response

    fun getTransactionsByType(@PathParam("type") type: String): Response

    fun getTransactionsByBudget(budgetId : Long) : Response
}