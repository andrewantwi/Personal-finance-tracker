package main.business.budgets.boudary.http

import jakarta.inject.Inject
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import main.business.budgets.dto.SetBudgetDto
import main.business.budgets.service.BudgetInt
import java.util.*


@Path("/tracker/categories")
@Produces(MediaType.APPLICATION_JSON)
class BudgetRoute : BudgetRouteInt {

    @Inject
    private lateinit var budgetService : BudgetInt

    override fun setBudget(setBudgetDto: SetBudgetDto): Response {
        val budget = budgetService.setBudget(setBudgetDto)

        return Response.status(Response.Status.CREATED)
            .entity(budget)
            .build()
    }

    override fun getBudgetByTime(time: Date): Response {
        TODO("Not yet implemented")
    }

    override fun getAllBudgets(): Response {
        TODO("Not yet implemented")
    }
}