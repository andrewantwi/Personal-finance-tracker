package main.business.user.repo

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
class UserRepo : PanacheRepositoryBase<User, Long>
