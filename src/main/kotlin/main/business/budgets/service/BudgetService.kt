package main.business.budgets.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import main.business.budgets.dto.SetBudgetDto
import main.business.budgets.repo.Budget
import main.business.budgets.repo.BudgetRepo
import java.util.*


@ApplicationScoped
class BudgetService : BudgetInt {

    @Inject
    private lateinit var budgetRepo: BudgetRepo

    override fun setBudget(setBudgetDto: SetBudgetDto): Budget {
        val budget = Budget()

        budget.name = setBudgetDto.name
        budget.value = setBudgetDto.value
        budget.endDate = setBudgetDto.endDate
        budget.startDate = setBudgetDto.startDate
         budgetRepo.persist(budget)

        return budget


    }

    override fun getBudgetByTime(time: Date): List<Budget> {
        val budgets = getAllBudgets()
        val specificBudgetsByTime = budgets.filter { it.endDate != null && it.endDate!! <= time }

        return specificBudgetsByTime
    }

    override fun getAllBudgets(): List<Budget> {
        return budgetRepo.listAll()
    }
}