package com.vickbt.carrystore.data.cache.sqldelight.daos

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.vickbt.carrystore.domain.models.Product
import com.vickbt.carrystore.utils.DatabaseDriverFactory
import com.vickbt.shared.data.cache.sqldelight.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class CartDao(private val databaseDriverFactory: DatabaseDriverFactory) {

    private val appDatabase = AppDatabase(databaseDriverFactory.createDriver())
    val dbQuery = appDatabase.appDatabaseQueries

    fun saveProduct(product: Product) {
        dbQuery.transaction {
            dbQuery.insertProduct(
                id = product.id.toLong(),
                name = product.name,
                description = product.description,
                price = product.price.toLong(),
                currencyCode = product.currencyCode,
                currencySymbol = product.currencySymbol,
                quantity = product.quantity?.toLong(),
                imageLocation = product.imageLocation,
                status = product.status
            )
        }
    }

    fun getAllProducts() = dbQuery.getAllProducts().asFlow().mapToList(Dispatchers.IO)

    fun getProduct(id: Int) =
        dbQuery.getProduct(id = id.toLong()).asFlow().mapToOneOrNull(Dispatchers.IO)

    fun deleteProduct(id: Int) = dbQuery.deleteProduct(id = id.toLong())

    fun deleteAllProducts() = dbQuery.deleteAllProducts()

    fun isProductInCart(id: Int) =
        dbQuery.isProductInCart(id = id.toLong()).asFlow().mapToOneOrNull(Dispatchers.IO)

}