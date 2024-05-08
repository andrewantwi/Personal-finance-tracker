package main.business.budgets.service

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
        try {
            return budgetRepo.listAll()
        } catch (e: Exception) {
            throw RuntimeException("Failed to retrieve all budgets: ${e.message}", e)
        }
    }

    override fun getBudgetById(id: Long): Budget {
        try {
            val budget = budgetRepo.findById(id)
            return budget ?: throw RuntimeException("Budget not found for ID: $id")
        } catch (e: Exception) {
            throw RuntimeException("Failed to retrieve budget by ID: $id - ${e.message}", e)
        }
    }
}
