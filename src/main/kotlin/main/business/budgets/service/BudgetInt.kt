package main.business.budgets.service

import main.business.budgets.dto.SetBudgetDto
import main.business.budgets.repo.Budget
import java.util.*

interface BudgetInt {
    fun setBudget (setBudgetDto: SetBudgetDto) : Budget

    fun getBudgetByTime(time: Date):  List<Budget>

    fun getAllBudgets() : List<Budget>
}