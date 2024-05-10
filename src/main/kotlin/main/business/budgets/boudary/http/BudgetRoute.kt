package main.business.budgets.boudary.http

import jakarta.inject.Inject
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import main.business.budgets.dto.SetBudgetDto
import main.business.budgets.service.BudgetInt
import jakarta.ws.rs.*
import main.business.budgets.dto.BudgetDto
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.stream.Collectors

@Path("/tracker/budgets")
@Produces(MediaType.APPLICATION_JSON)
class BudgetRoute : BudgetRouteInt {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    val modelMapper = ModelMapper()

    @Inject
    private lateinit var budgetService : BudgetInt

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    override fun setBudget(setBudgetDto: SetBudgetDto): Response {
        logger.info("About to make request to set budget")
        val budget = budgetService.setBudget(setBudgetDto)

        return Response.status(Response.Status.CREATED)
            .entity(budget)
            .build()
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    override fun editBudget(setBudgetDto: SetBudgetDto): Response {
        logger.info("Request to edit budget")
        val budget = budgetService.editBudget(setBudgetDto)
        val budgetDto = modelMapper.map(budget, BudgetDto::class.java)
        logger.info("response for edit Budget {}", budgetDto)

        return Response.ok(budgetDto).build()
    }

    @GET
    @Path("/{id}")
    override fun getBudgetById(@PathParam("id") id: Long): Response {
        logger.info("Request to get budget")
        val budget = budgetService.getBudgetById(id)
        val budgetDto = modelMapper.map(budget, BudgetDto::class.java)

        return Response.ok(budgetDto).build()
    }


    @GET
    override fun getAllBudgets(): Response {
        logger.info("Request to get all budgets")
        val budgets = budgetService.getAllBudgets()
        val dtos = budgets
            .stream()
            .map { modelMapper.map(it, BudgetDto::class.java) }
            .collect(Collectors.toList())
        logger.info("response for get All budgets {}", dtos)

        return Response.ok(dtos).build()
    }
}