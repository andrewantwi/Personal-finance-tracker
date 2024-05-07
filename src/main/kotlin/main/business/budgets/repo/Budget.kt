package main.business.budgets.repo

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import java.util.*

class Budget : PanacheEntityBase {
    var name: String ? = null
    var value : Double ? = null
    var endDate : Date? = null
    var startDate : Date? = null

}