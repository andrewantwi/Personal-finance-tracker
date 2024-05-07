package main.business.budgets.repo

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped

 @ApplicationScoped
class BudgetRepo : PanacheRepositoryBase<Budget, Long>
