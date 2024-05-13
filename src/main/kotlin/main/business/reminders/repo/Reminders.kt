package main.business.reminders.repo

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
class Reminders {
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


}