package main.business.categories.dto

import jakarta.validation.constraints.NotNull
import java.io.Serializable
import java.time.LocalDateTime

class CreateCategoryDto : Serializable {

    @NotNull
    var name : String? = null

    var date: LocalDateTime? = null

    var description: String? = null

    val version: Long = 0

}