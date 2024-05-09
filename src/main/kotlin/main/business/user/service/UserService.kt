package main.business.user.service

import com.arjuna.ats.arjuna.logging.tsLogger.logger
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.NotFoundException
import main.business.user.dto.AddUserDto
import main.business.user.dto.EditUserDto
import main.business.user.repo.User
import main.business.user.repo.UserRepo
import java.time.LocalDate

@ApplicationScoped
class UserService : UserServiceInt {
    @Inject
    private lateinit var userRepo: UserRepo

    override fun addUser(addUserDto: AddUserDto): User {

        logger.info("About adding transaction")
        try {
            val user = User()

            user.userName = addUserDto.userName
            user.userEmail = addUserDto.userEmail
            user.created= addUserDto.created

            userRepo.persist(user)

            return user
        } catch (e: Exception) {
            throw RuntimeException("Failed to add user: ${e.message}", e)
        }
    }

    override fun editUser(editUserDto: EditUserDto): User {
        try {
            val user = getUserById(editUserDto.id!!)

            user.userEmail = editUserDto.userEmail
            user.userName = editUserDto.userName
            user.updated = LocalDate.now()


            return user
        } catch (e: Exception) {
            throw RuntimeException("Failed to edit user: ${e.message}", e)
        }
    }

    override fun getUserById(id: Long): User {
        try {
            return userRepo.findById(id) ?: throw NotFoundException("Transaction not found with ID: $id")
        } catch (e: Exception) {
            throw RuntimeException("Failed to retrieve User by ID::-> $id - ${e.message}", e)
        }
    }

    override fun getAllUsers(): List<User> {
        try {
            return userRepo.listAll()
        } catch (e: Exception) {
            throw RuntimeException("Failed to retrieve all Users: ${e.message}", e)
        }
    }

    override fun deleteUser(id: Long): String {
        try {
            val user = getUserById(id)
            userRepo.delete(user)
            return "Deleted successfully"
        } catch (e: Exception) {
            throw RuntimeException("Failed to delete user with ID: $id - ${e.message}", e)
        }
    }
}