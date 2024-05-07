package main.business.transactions.repo
import main.business.transactions.enums.TransactionType
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import main.business.categories.repo.Category
import java.io.Serializable
import java.math.BigDecimal
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
    var amount: BigDecimal? = null

    @Column(name = "description")
    var description: String? = null


    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinColumn(name = "category_id")
    var category: Category? = null

    @Column(name = "date")
    var date: LocalDateTime? = null

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