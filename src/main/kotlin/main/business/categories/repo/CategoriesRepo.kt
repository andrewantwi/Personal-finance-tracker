package main.business.categories.repo

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped

    @ApplicationScoped
    class CategoriesRepo : PanacheRepositoryBase<Category, Long>
