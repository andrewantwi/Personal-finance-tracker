package main.business.reminders.repo

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import main.business.reminders.enums.ReminderType
import main.business.transactions.enums.TransactionType
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

@Entity
class Reminder : PanacheEntityBase, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name")
    var name : String? = null

    @Column(name = "date")
    var time : Date? = null

    @Column(name = "description")
    var description : String? = null

    @CreationTimestamp
    @Column(name = "created")
    var created: LocalDateTime? = null

    @UpdateTimestamp
    @Column(name = "updated")
    var updated: LocalDateTime? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    var reminderType: ReminderType? = null

}