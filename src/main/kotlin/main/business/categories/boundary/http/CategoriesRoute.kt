package main.business.categories.boundary.http

import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import main.business.categories.dto.CategoryDto
import main.business.categories.dto.CreateCategoryDto
import main.business.categories.dto.EditCategoryDto
import main.business.categories.service.CategoriesServiceInt
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.stream.Collectors


@Path("/tracker/categories")
@Produces(MediaType.APPLICATION_JSON)
class CategoriesRoute : CategoriesInt {

    @Inject
    private lateinit var categoriesService: CategoriesServiceInt

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    val modelMapper = ModelMapper()

    @POST
    override fun createCategory(createCategoryDto: CreateCategoryDto): Response {
        val category = categoriesService.createCategory(createCategoryDto)

        logger.info("Request to create a new category {}", category)


        return Response.status(Response.Status.CREATED)
            .entity(category)
            .build()
    }

    @GET
    @Path("/{id}")
    override fun getCategory(@PathParam("id") id: Long): Response {
        logger.info("Request to get to category with id ::-> $id")

        val category = categoriesService.getCategoryByID(id)

        val categoryDto = modelMapper.map(category, CategoryDto::class.java)

        logger.info("Category with $id ::-> {}",categoryDto)

        return Response.ok(categoryDto).build()
    }

    @PUT
    override fun editCategory(editCategoryDto: EditCategoryDto): Response {
        logger.info("response to edit category ")

        val category = getCategory(editCategoryDto.id!!)
        val categoryDto = modelMapper.map(category, CategoryDto::class.java)
        logger.info("response for edit Post {}", categoryDto)

        return Response.ok(categoryDto).build()
    }

    @DELETE
    @Path("/{id}")
    override fun deleteCategory(@PathParam("id") id: Long): Response {
        logger.info("Request to delete Category with id ::-> $id")
        val deletedCategory = categoriesService.deleteCategory(id)


        return Response.ok(deletedCategory).build()
    }

    @GET
    override fun getAllCategories(): Response {
       logger.info("Request to get all Categories")
        val categories = categoriesService.getAllCategories()

        val dtos = categories
            .stream()
            .map { modelMapper.map(it, CategoryDto::class.java) }
            .collect(Collectors.toList())
        logger.info("response for get All Categories {}", dtos)

        return Response.ok(dtos).build()
    }


}