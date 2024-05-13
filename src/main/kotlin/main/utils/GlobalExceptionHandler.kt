package main.utils

import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Provider
class GlobalExceptionHandler : ExceptionMapper<Exception> {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun toResponse(p0: Exception?): Response {
        val errorMessage = "An unexpected error occurred"
        logger.error(p0?.message)
        return  Response.serverError().entity(errorMessage).build()
    }

}