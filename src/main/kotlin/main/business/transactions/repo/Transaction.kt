package main.business.transactions.repo
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import main.business.transactions.enums.TransactionType
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import main.business.budgets.repo.Budget
import main.business.categories.repo.Category
import java.io.Serializable
import java.time.LocalDateTime
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp

@Entity
class Transaction : PanacheEntityBase, Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "user_id")
    var userId: Long? = null

    @Column(name = "amount")
    var amount: Double? = null

    @Column(name = "description")
    var description: String? = null

    @JsonManagedReference
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id")
    var budget: Budget? = null

    @JsonManagedReference
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    var category: Category? = null

    @Column(name = "account_id")
    var accountId: Long? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    var transactionType: TransactionType? = null

    @CreationTimestamp
    @Column(name = "created")
    var created: LocalDateTime? = null

    @UpdateTimestamp
    @Column(name = "updated")
    var updated: LocalDateTime? = null

    @Version
    val version: Long = 0
}