package main.business.categories.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.NotFoundException
import main.business.categories.dto.CreateCategoryDto
import main.business.categories.dto.EditCategoryDto
import main.business.categories.repo.CategoriesRepo
import main.business.categories.repo.Category
import java.time.LocalDateTime

@ApplicationScoped
class CategoriesServiceService : CategoriesServiceInt {
    @Inject
    private lateinit var categoriesRepo: CategoriesRepo

    @Transactional
    override fun createCategory(createCategoryDto: CreateCategoryDto): Category {
        val category = Category()

        category.name = createCategoryDto.name
        category.description = createCategoryDto.description
        category.date = LocalDateTime.now()
        category.created = LocalDateTime.now()

        categoriesRepo.persist(category)

        return category
    }

    override fun getAllCategories(): List<Category> {
        return categoriesRepo.listAll()
    }

    override fun getCategoryByID(id: Long): Category {
        return categoriesRepo.findById(id) ?: throw NotFoundException("category not found with ID: $id")
    }

    override fun editCategory(editCategoryDto: EditCategoryDto): Category {
        val category = getCategoryByID(editCategoryDto.id!!)

        category.name = editCategoryDto.name
        category.description = editCategoryDto.description
        category.date = editCategoryDto.date
        category.updated = LocalDateTime.now()
        category.created = editCategoryDto.created

        categoriesRepo.persistAndFlush(category)


        return category

    }

    override fun deleteCategory(id:Long): String {
        val category = getCategoryByID(id)
        categoriesRepo.delete(category)
        return "Deleted successfully category with id ::-> $id"
    }
}