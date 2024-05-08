package main.business.categories.repo
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import main.business.transactions.repo.Transaction
import java.time.LocalDateTime

@Entity
class Category : PanacheEntityBase  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name")
    var name: String? = null

    @Column(name = "description")
    var description: String? = null

    @Column(name = "date")
    var date: LocalDateTime? = null

    @Column(name = "updated")
    var updated: LocalDateTime? = null

    @Column(name = "created")
    var created: LocalDateTime? = null

    @Version
    val version: Long = 0

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var transactions: MutableList<Transaction> = mutableListOf()

}