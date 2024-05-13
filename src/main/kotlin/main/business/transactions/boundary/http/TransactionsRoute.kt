package main.business.transactions.boundary.http

import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import main.business.transactions.dto.*
import main.business.transactions.enums.TransactionType
import main.business.transactions.service.transactions.TransactionServiceInt
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.modelmapper.ModelMapper
import java.util.stream.Collectors


@Path("/tracker/transactions")
@Produces(MediaType.APPLICATION_JSON)
class TransactionsRoute : TransactionsInt {
    @Inject
    private lateinit var transactionService: TransactionServiceInt

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    val modelMapper = ModelMapper()


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    override fun addTransaction(addTransactionDto:AddTransactionDto): Response {
        logger.info("Request to create a new Transaction")

        val transactionDto = transactionService.addTransaction(addTransactionDto)

        logger.info("Request to create a new Transaction {}", transactionDto)


        return Response.status(Response.Status.CREATED)
            .entity(transactionDto)
            .build()
    }

    @PUT
    @Path("/set-budget")
    @Consumes(MediaType.APPLICATION_JSON)
    override fun setBudgetForTransaction(setBudgetForTransactionDto: SetBudgetForTransactionDto): Response {
        try {
            transactionService.setBudgetForTransaction(setBudgetForTransactionDto)
            return Response.ok().build()
        } catch (e: Exception) {
            return Response.serverError().entity("Failed to set budget for transaction: ${e.message}").build()
        }
    }

    @PUT
    @Path("/set-category")
    @Consumes(MediaType.APPLICATION_JSON)
    override fun setCategoryForTransaction(setCategoryForTransactionDto: SetCategoryForTransactionDto): Response {
        try {
            transactionService.setCategoryForTransaction(setCategoryForTransactionDto)
            return Response.ok().build()
        } catch (e: Exception) {
            return Response.serverError().entity("Failed to set budget for transaction: ${e.message}").build()
        }
    }

    @GET
    @Path("/{id}")
    override fun getTransaction(@PathParam("id") id: Long): Response {
        val transaction = transactionService.getTransactionByID(id)
        val transactionDto = modelMapper.map(transaction, TransactionDto::class.java)

        return Response.ok(transactionDto).build()
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    override fun editTransaction(editTransactionDto:EditTransactionDto): Response {
        val transaction = transactionService.editTransaction(editTransactionDto)
        val transactionDto = modelMapper.map(transaction, TransactionDto::class.java)
        logger.info("response for edit Post {}", transactionDto)

        return Response.ok(transactionDto).build()
    }
    @DELETE
    override fun deleteTransaction(@PathParam("id") id: Long): Response {
        val deletedTransaction = transactionService.deleteTransaction(id)


        return Response.ok(deletedTransaction).build()
    }

    @GET
    override fun getAllTransactions(): Response {
        logger.info("Request to get all Transactions")
        val transactions = transactionService.getAllTransactions()

        val dtos = transactions
            .stream()
            .map { modelMapper.map(it, TransactionDto::class.java) }
            .collect(Collectors.toList())
        logger.info("response for get All Transactions {}", dtos)

        return Response.ok(dtos).build()
    }

    @GET
    @Path("type/{type}")
    override fun getTransactionsByType(@PathParam("type") type: String): Response {
        logger.info("Request to get all Transactions by Type ::->$type")
        val transactionTypeUpper = type.uppercase()
        val transactionType = try {
            TransactionType.valueOf(transactionTypeUpper)
        } catch (e: IllegalArgumentException) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid transaction type").build()
        }

        val transactions = transactionService.getTransactionsByType(transactionType)

        val dtos = transactions
            .stream()
            .map { modelMapper.map(it, TransactionDto::class.java) }
            .collect(Collectors.toList())
        logger.info("response for get Transactions by type {}", dtos)

        return Response.ok(dtos).build()
    }


    @GET
    @Path("get-by-budget/{budgetId}")
    override fun getTransactionsByBudget(@PathParam("budgetId") budgetId: Long): Response {
        logger.info("Request to get all Transactions by Budget ID ::->$budgetId")

        val transactions = transactionService.getTransactionsByBudget(budgetId)

        val dtos = transactions
            .stream()
            .map { modelMapper.map(it, TransactionDto::class.java) }
            .collect(Collectors.toList())
        return Response.ok(dtos).build()
    }


}