package main.business.categories.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

class EditCategoryDto {

    @NotNull
    var name: String? = null

    @NotBlank
    var id: Long? = null

    var description: String? = null

    var created: LocalDateTime? = null


    var updated: LocalDateTime? = null

    val version: Long = 0

    var date: LocalDateTime? = null
}