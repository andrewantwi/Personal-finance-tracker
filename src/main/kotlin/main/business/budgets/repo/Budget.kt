package main.business.budgets.repo

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import main.business.budgets.enums.BudgetType
import main.business.transactions.repo.Transaction
import java.util.*

@Entity
class Budget : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToMany(mappedBy = "budget", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var transactions: MutableList<Transaction> = mutableListOf()

    @Column(name = "name")
    var name: String ? = null

    @Column(name = "set_amount")
    var setAmount : Double ? = null

    @Column(name = "remaining_amount")
    var remainingAmount : Double ? = null

    @Column(name = "created")
    var created : Date? = null

    @Column(name = "budget_type")
    var budgetType : BudgetType? = null

}