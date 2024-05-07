package main.business.categories.boundary.http


import jakarta.ws.rs.core.Response
import main.business.categories.dto.CreateCategoryDto
import main.business.categories.dto.EditCategoryDto


interface CategoriesInt {
    fun createCategory(createCategoryDto: CreateCategoryDto): Response

    fun getCategory(id:Long): Response

    fun editCategory(editCategoryDto: EditCategoryDto): Response

    fun deleteCategory(id : Long): Response

    fun getAllCategories(): Response
}