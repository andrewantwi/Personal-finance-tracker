package main.business.user.boundary

import jakarta.ws.rs.core.Response
import main.business.user.dto.AddUserDto
import main.business.user.dto.EditUserDto

interface UserRouteInt {
    fun addUser (addUserDto: AddUserDto) : Response

    fun editUser (editUserDto: EditUserDto) : Response

    fun getUserById (id: Long) : Response

    fun getAllUsers () : Response

    fun deleteUser(id: Long) : Response
}