package main.business.transactions.boundary.http

import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import main.business.transactions.dto.CreateTransactionDto
import main.business.transactions.dto.EditTransactionDto
import main.business.transactions.dto.TransactionDto
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
    private lateinit var service: TransactionServiceInt

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    val modelMapper = ModelMapper()


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    override fun createTransaction(createTransactionDto:CreateTransactionDto): Response {
        val transactionDto = service.createTransaction(createTransactionDto)

        logger.info("Request to create a new Transaction {}", transactionDto)


        return Response.status(Response.Status.CREATED)
            .entity(transactionDto)
            .build()
    }

    @GET
    @Path("/{id}")
    override fun getTransaction(@PathParam("id") id: Long): Response {
        val transaction = service.getTransactionByID(id)
        val transactionDto = modelMapper.map(transaction, TransactionDto::class.java)

        return Response.ok(transactionDto).build()
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    override fun editTransaction(editTransactionDto:EditTransactionDto): Response {
        val transaction = service.editTransaction(editTransactionDto)
        val transactionDto = modelMapper.map(transaction, TransactionDto::class.java)
        logger.info("response for edit Post {}", transactionDto)

        return Response.ok(transactionDto).build()
    }
    @DELETE
    override fun deleteTransaction(@PathParam("id") id: Long): Response {
        val deletedTransaction = service.deleteTransaction(id)


        return Response.ok(deletedTransaction).build()
    }

    @GET
    override fun getAllTransactions(): Response {
        logger.info("Request to get all Transactions")
        val transactions = service.getAllTransactions()

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

        val transactions = service.getTransactionsByType(transactionType)

        val dtos = transactions
            .stream()
            .map { modelMapper.map(it, TransactionDto::class.java) }
            .collect(Collectors.toList())
        logger.info("response for get Transactions by type {}", dtos)

        return Response.ok(dtos).build()
    }


}