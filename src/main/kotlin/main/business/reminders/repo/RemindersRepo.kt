package main.business.reminders.repo

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class RemindersRepo : PanacheRepositoryBase<Reminder, Long> {
}