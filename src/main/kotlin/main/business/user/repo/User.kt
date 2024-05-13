package main.business.user.repo

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate


@Entity
class User : PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var userName : String = ""
    var userEmail : String = ""
    var created : LocalDate? = null
    var updated : LocalDate? = null
    var amountSpent: Double = 0.0
    var amountReceived: Double = 0.0
    var amountSaved: Double = 0.0
    var totalAmount : Double = 0.0
}