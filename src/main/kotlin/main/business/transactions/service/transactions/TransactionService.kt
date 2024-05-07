package main.business.transactions.service.transactions

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.NotFoundException
import main.business.transactions.dto.CreateTransactionDto
import main.business.transactions.dto.EditTransactionDto
import main.business.transactions.enums.TransactionType
import main.business.transactions.repo.Transaction
import main.business.transactions.repo.TransactionsRepo

@ApplicationScoped
class TransactionService : TransactionServiceInt {
    @Inject
    private lateinit var transactionsRepo: TransactionsRepo

    @Transactional
    override fun createTransaction(createTransactionDto: CreateTransactionDto): Transaction {
        val transaction = Transaction()

        transaction.userId = createTransactionDto.userId
        transaction.amount = createTransactionDto.amount
        transaction.description = createTransactionDto.description
        transaction.category = createTransactionDto.category
        transaction.date = createTransactionDto.date

        transaction.transactionType = createTransactionDto.transactionType
        transactionsRepo.persist(transaction)

        return transaction
    }

    override fun getAllTransactions(): List<Transaction> {
        return transactionsRepo.listAll()
    }

    override fun getTransactionsByType(type : TransactionType): List<Transaction> {
        val transactions : List<Transaction> = getAllTransactions()
       val transactionsOfType = transactions.filter{ it.transactionType == type}

        return transactionsOfType
    }

    override fun getTransactionByID(id: Long): Transaction {
        return transactionsRepo.findById(id) ?: throw NotFoundException("Transaction not found with ID: $id")
    }

    override fun editTransaction(editTransactionDto: EditTransactionDto): Transaction {
        val transaction = getTransactionByID(editTransactionDto.id!!)

        transaction.userId = editTransactionDto.userId
        transaction.amount = editTransactionDto.amount
        transaction.description = editTransactionDto.description
        transaction.category = editTransactionDto.category
        transaction.date = editTransactionDto.date
        transaction.transactionType = editTransactionDto.transactionType
        transactionsRepo.persist(transaction)

        return transaction

    }

    override fun deleteTransaction(id:Long): String {
        val transaction = getTransactionByID(id)
        transactionsRepo.delete(transaction)
        return "Deleted successfully"
    }
}