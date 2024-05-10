package main.utils

import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.*

@Provider
class GlobalExceptionHandler : ExceptionMapper<Exception> {
    override fun toResponse(p0: Exception?): Response {
        val errorMessage = "An unexpected error occurred"
        return  Response.serverError().entity(errorMessage).build()
    }

}