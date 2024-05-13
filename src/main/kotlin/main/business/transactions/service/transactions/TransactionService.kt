package main.business.transactions.service.transactions

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.NotFoundException
import main.business.budgets.service.BudgetService
import main.business.categories.repo.CategoriesRepo
import main.business.categories.service.CategoriesService
import main.business.transactions.dto.AddTransactionDto
import main.business.transactions.dto.EditTransactionDto
import main.business.transactions.dto.SetBudgetForTransactionDto
import main.business.transactions.dto.SetCategoryForTransactionDto
import main.business.transactions.enums.TransactionType
import main.business.transactions.repo.Transaction
import main.business.transactions.repo.TransactionsRepo
import main.business.user.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@ApplicationScoped
class TransactionService : TransactionServiceInt {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Inject
    private lateinit var transactionsRepo: TransactionsRepo

    @Inject
    private lateinit var budgetService: BudgetService

    @Inject
    private lateinit var categoriesService: CategoriesService

    @Inject
    private lateinit var userService: UserService


    @Inject
    private lateinit var categoriesRepo: CategoriesRepo

    @Transactional
    override fun addTransaction(addTransactionDto: AddTransactionDto): Transaction {

        logger.info("About adding transaction")
        try {
            val transaction = Transaction()


            transaction.userId = addTransactionDto.userId
            transaction.amount = addTransactionDto.amount
            transaction.description = addTransactionDto.description
            transaction.transactionType = addTransactionDto.transactionType
            if (addTransactionDto.budgetId!= null){
                val budget = budgetService.getBudgetById(addTransactionDto.budgetId!!)
                transaction.budget = budget
            }
            if (addTransactionDto.categoryId!= null){
                val category = categoriesService.getCategoryByID(addTransactionDto.categoryId!!)
                transaction.category = category
            }
            userService.updateUserAmount(addTransactionDto.amount!! , addTransactionDto.transactionType!!, addTransactionDto.userId!!)

            transactionsRepo.persist(transaction)

            return transaction
        } catch (e: Exception) {
            throw RuntimeException("Failed to add transaction: ${e.message}", e)
        }
    }

    override fun getAllTransactions(): List<Transaction> {
        try {
            return transactionsRepo.listAll()
        } catch (e: Exception) {
            throw RuntimeException("Failed to retrieve all transactions: ${e.message}", e)
        }
    }

    override fun getTransactionsByType(type: TransactionType): List<Transaction> {
        try {
            val transactions: List<Transaction> = getAllTransactions()
            return transactions.filter { it.transactionType == type }
        } catch (e: Exception) {
            throw RuntimeException("Failed to retrieve transactions by type: ${e.message}", e)
        }
    }

    override fun getTransactionsByBudget(budgetId: Long): List<Transaction> {
        return getAllTransactions().filter { it.budget?.id == budgetId }
    }

    @Transactional
    override fun setBudgetForTransaction(setBudgetForTransactionDto: SetBudgetForTransactionDto) : Transaction {
        try {
            val transaction = transactionsRepo.findById(setBudgetForTransactionDto.transactionId!!) ?: throw NotFoundException("No transaction found with this ID ::-> ${setBudgetForTransactionDto.transactionId}")

                val budgetForSum = budgetService.getBudgetById(setBudgetForTransactionDto.budgetId!!)
                if (transaction.transactionType == TransactionType.INCOME){
                    budgetForSum.remainingAmount = budgetForSum.remainingAmount?.plus(transaction.amount!!)
                } else {
                    budgetForSum.remainingAmount = budgetForSum.remainingAmount?.minus(transaction.amount!!)
                }
            transaction.budget = budgetForSum
            transactionsRepo.persistAndFlush(transaction)
            return transaction
        } catch (e: Exception) {
            throw RuntimeException("Failed to set budget for transaction: ${e.message}", e)
        }
    }

    @Transactional
    override fun setCategoryForTransaction(setCategoryForTransactionDto: SetCategoryForTransactionDto) {
        try {
            val transaction = transactionsRepo.findById(setCategoryForTransactionDto.transactionId!!)

            val category = categoriesRepo.findById(setCategoryForTransactionDto.categoryId!!)

            transaction?.category = category
        } catch (e: Exception) {
            throw RuntimeException("Failed to set budget for transaction: ${e.message}", e)
        }
    }

    override fun getTransactionByID(id: Long): Transaction {
        try {
            return transactionsRepo.findById(id) ?: throw NotFoundException("Transaction not found with ID: $id")
        } catch (e: Exception) {
            throw RuntimeException("Failed to retrieve transaction by ID::-> $id - ${e.message}", e)
        }
    }

    override fun editTransaction(editTransactionDto: EditTransactionDto): Transaction {
        try {
            val transaction = getTransactionByID(editTransactionDto.id!!)

            transaction.userId = editTransactionDto.userId
            transaction.amount = editTransactionDto.amount
            transaction.description = editTransactionDto.description
            transaction.category = editTransactionDto.category
            transaction.transactionType = editTransactionDto.transactionType
            transactionsRepo.persistAndFlush(transaction)

            return transaction
        } catch (e: Exception) {
            throw RuntimeException("Failed to edit transaction: ${e.message}", e)
        }
    }

    override fun deleteTransaction(id: Long): String {
        try {
            val transaction = getTransactionByID(id)
            transactionsRepo.delete(transaction)
            return "Deleted successfully"
        } catch (e: Exception) {
            throw RuntimeException("Failed to delete transaction with ID: $id - ${e.message}", e)
        }
    }
}
