package main.business.user.boundary

import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import main.business.user.dto.AddUserDto
import main.business.user.dto.EditUserDto
import main.business.user.dto.UserDto
import main.business.user.service.UserServiceInt
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.stream.Collectors

@Path("/tracker/users")
@Produces(MediaType.APPLICATION_JSON)
class UserRoute : UserRouteInt {

    @Inject
    private lateinit var userService: UserServiceInt

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    val modelMapper = ModelMapper()

    @POST
    override fun addUser(addUserDto: AddUserDto): Response {
        val user = userService.addUser(addUserDto)

        logger.info("Request to create a new Transaction {}", user)


        return Response.status(Response.Status.CREATED)
            .entity(user)
            .build()
    }

    @PUT
    override fun editUser(editUserDto: EditUserDto): Response {
        val user = userService.editUser(editUserDto)
        val userDto = modelMapper.map(user, UserDto::class.java)
        logger.info("response for edit Post {}", userDto)

        return Response.ok(userDto).build()
    }
    @GET
    @Path("/{id}")
    override fun getUserById(@PathParam("id") id: Long): Response {
        val user = userService.getUserById(id)
        val transactionDto = modelMapper.map(user, UserDto::class.java)

        return Response.ok(transactionDto).build()
    }



    @GET
    override fun getAllUsers(): Response {
        logger.info("Request to get all Transactions")
        val users = userService.getAllUsers()

        val dtos = users
            .stream()
            .map { modelMapper.map(it, UserDto::class.java) }
            .collect(Collectors.toList())
        logger.info("response for get All Users {}", dtos)

        return Response.ok(dtos).build()
    }

    @DELETE
    @Path("/{id}")
    override fun deleteUser(@PathParam("id") id: Long): Response {
        val deletedTransaction = userService.deleteUser(id)


        return Response.ok(deletedTransaction).build()
    }
}