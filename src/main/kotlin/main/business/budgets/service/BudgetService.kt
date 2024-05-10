package main.business.budgets.service

import com.arjuna.ats.arjuna.logging.tsLogger.logger
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import main.business.budgets.dto.SetBudgetDto
import main.business.budgets.repo.Budget
import main.business.budgets.repo.BudgetRepo

@ApplicationScoped
class BudgetService : BudgetInt {

    @Inject
    private lateinit var budgetRepo: BudgetRepo

    @Transactional
    override fun setBudget(setBudgetDto: SetBudgetDto): Budget {
        try {
            logger.info("About to Set budget")
            val budget = Budget()

            budget.name = setBudgetDto.name
            budget.setAmount = setBudgetDto.setAmount
            budget.remainingAmount = setBudgetDto.setAmount
            budget.budgetType = setBudgetDto.budgetType
            budgetRepo.persist(budget)

            return budget
        } catch (e: Exception) {
            throw RuntimeException("Failed to set budget: ${e.message}", e)
        }
    }

    @Transactional
    override fun editBudget(setBudgetDto: SetBudgetDto): Budget {
        try {
            logger.info("About to edit ${setBudgetDto.name} with id :::-> ${setBudgetDto.id}")
            val budget = getBudgetById(setBudgetDto.id ?: throw IllegalArgumentException("Budget ID cannot be null"))

            budget.name = setBudgetDto.name
            budget.setAmount = setBudgetDto.setAmount
            budget.remainingAmount = setBudgetDto.setAmount
            budget.budgetType = setBudgetDto.budgetType

            budgetRepo.persistAndFlush(budget)

            return budget
        } catch (e: Exception) {
            throw RuntimeException("Failed to edit budget: ${e.message}", e)
        }
    }

    override fun getAllBudgets(): List<Budget> {
        logger.info("About to get all budgets")
        try {
            val budgets = budgetRepo.listAll()
            logger.info("these are the budgets ::-> $budgets")
            return budgets
        } catch (e: Exception) {
            throw RuntimeException("Failed to retrieve all budgets: ${e.message}", e)
        }
    }

    override fun getBudgetById(id: Long): Budget {
        try {

            val budget = budgetRepo.findById(id)
            logger.info("Budget with id ::-> $id is $budget")
            return budget ?: throw RuntimeException("Budget not found for ID: $id")
        } catch (e: Exception) {
            throw RuntimeException("Failed to retrieve budget by ID: $id - ${e.message}", e)
        }
    }
}
