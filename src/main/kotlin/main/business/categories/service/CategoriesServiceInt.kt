package main.business.categories.service

import main.business.categories.dto.CreateCategoryDto
import main.business.categories.dto.EditCategoryDto
import main.business.categories.repo.Category

interface CategoriesServiceInt {
    fun createCategory(createCategoryDto: CreateCategoryDto): Category

    fun getCategoryByID(id: Long): Category

    fun editCategory(editCategoryDto: EditCategoryDto): Category

    fun deleteCategory(id:Long): String

    fun getAllCategories(): List<Category>
}