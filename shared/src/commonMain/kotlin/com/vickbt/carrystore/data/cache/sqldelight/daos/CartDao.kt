package com.vickbt.carrystore.data.cache.sqldelight.daos

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import app.cash.sqldelight.db.SqlDriver
import com.vickbt.carrystore.domain.models.Product
import com.vickbt.shared.data.cache.sqldelight.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class CartDao(private val sqlDriver: SqlDriver) {

    private val appDatabase = AppDatabase(sqlDriver)
    val dbQuery = appDatabase.appDatabaseQueries

    suspend fun saveProduct(product: Product) = withContext(Dispatchers.IO) {
        dbQuery.transaction {
            dbQuery.insertProduct(
                id = product.id.toLong(),
                name = product.name,
                description = product.description,
                price = product.price.toLong(),
                currencyCode = product.currencyCode,
                currencySymbol = product.currencySymbol,
                quantity = product.quantity.toLong(),
                imageLocation = product.imageLocation,
                status = product.status,
                cartQuantity = product.cartQuantity?.toLong(),
                createdAt = product.createdAt
            )
        }
    }

    fun getAllProducts() = dbQuery.getAllProducts().asFlow().mapToList(Dispatchers.IO)

    fun getProduct(id: Int) =
        dbQuery.getProduct(id = id.toLong()).asFlow().mapToOneOrNull(Dispatchers.IO)

    suspend fun deleteProduct(id: Int) = withContext(Dispatchers.IO) {
        dbQuery.deleteProduct(id = id.toLong())
    }

    suspend fun deleteAllProducts() = withContext(Dispatchers.IO) {
        dbQuery.deleteAllProducts()
    }
}
