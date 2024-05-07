package main.business.budgets.boudary.http

import jakarta.inject.Inject
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import main.business.budgets.dto.SetBudgetDto
import java.util.*


@Path("/tracker/categories")
@Produces(MediaType.APPLICATION_JSON)
class BoundaryRoute : BoundaryInt {

    @Inject
    private lateinit var boundaryService : BoundaryInt

    override fun setBudget(setBudgetDto: SetBudgetDto): Response {
        val budget = boundaryService.setBudget(setBudgetDto)

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