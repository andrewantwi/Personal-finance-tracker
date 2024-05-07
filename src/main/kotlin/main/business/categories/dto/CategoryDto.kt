package main.business.categories.dto

import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

class CategoryDto {


    @NotNull
    var name : String? = null


    var description: String? = null


    var created: LocalDateTime? = null


    var updated: LocalDateTime? = null


    var date: LocalDateTime? = null

    val version: Long = 0


}