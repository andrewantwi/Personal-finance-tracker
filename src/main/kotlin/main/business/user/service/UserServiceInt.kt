package main.business.user.service

import main.business.transactions.enums.TransactionType
import main.business.user.dto.AddUserDto
import main.business.user.dto.EditUserDto
import main.business.user.repo.User

interface UserServiceInt {
    fun addUser(addUserDto: AddUserDto) : User

    fun editUser(editUserDto: EditUserDto) : User

    fun getUserById(id: Long) : User

    fun getAllUsers() : List<User>

    fun deleteUser(id: Long) : String

    fun updateUserAmount(  amount: Double , type : TransactionType, userId : Long) : User


}