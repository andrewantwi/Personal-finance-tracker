package main.business.budgets.dto

import main.business.budgets.enums.BudgetType
import java.util.*

class BudgetDto{

    var id: Long? = null

    var name: String ? = null

    var setAmount : Double ? = null

    var remainingAmount : Double ? = null

    var created : Date? = null

    var budgetType : BudgetType? = null

}