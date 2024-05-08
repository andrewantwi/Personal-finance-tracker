package main.business.budgets.boudary.http

import jakarta.ws.rs.core.Response
import main.business.budgets.dto.SetBudgetDto

interface BudgetRouteInt {
    fun setBudget (setBudgetDto: SetBudgetDto) : Response

    fun editBudget(setBudgetDto: SetBudgetDto): Response
    fun getBudgetById (id: Long): Response
    fun getAllBudgets() : Response
}