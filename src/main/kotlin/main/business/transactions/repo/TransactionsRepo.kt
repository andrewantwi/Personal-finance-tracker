package main.business.transactions.repo

import jakarta.enterprise.context.ApplicationScoped
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase

@ApplicationScoped
class TransactionsRepo : PanacheRepositoryBase<Transaction, Long>{

}


