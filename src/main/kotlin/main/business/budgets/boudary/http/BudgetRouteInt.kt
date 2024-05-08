package main.business.budgets.boudary.http

import jakarta.ws.rs.core.Response
import main.business.budgets.dto.SetBudgetDto
import java.util.*

interface BudgetRouteInt {
    fun setBudget (setBudgetDto: SetBudgetDto) : Response

    fun getBudgetByTime(time: Date):  Response

    fun getAllBudgets() : Response
}