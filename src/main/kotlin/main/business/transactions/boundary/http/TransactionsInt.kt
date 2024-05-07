package main.business.transactions.boundary.http

import jakarta.ws.rs.PathParam
import jakarta.ws.rs.core.Response
import main.business.transactions.dto.CreateTransactionDto
import main.business.transactions.dto.EditTransactionDto

interface TransactionsInt {
    fun createTransaction(createTransactionDto: CreateTransactionDto): Response

    fun getTransaction(id:Long): Response

    fun editTransaction(editTransactionDto: EditTransactionDto): Response

    fun deleteTransaction(id : Long): Response

    fun getAllTransactions(): Response

    fun getTransactionsByType(@PathParam("type") type: String): Response


}