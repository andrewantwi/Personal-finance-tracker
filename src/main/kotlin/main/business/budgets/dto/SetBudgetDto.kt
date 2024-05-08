package main.business.budgets.dto

import main.business.budgets.enums.BudgetType
import java.time.LocalDate

class SetBudgetDto {
    val id : Long ? = null
    val name: String ? = null
    val setAmount : Double ? = null
    val date : LocalDate? = LocalDate.now()

    val budgetType : BudgetType ? = null

}