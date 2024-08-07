package com.vickbt.carrystore.domain.repositories

import com.vickbt.carrystore.domain.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCartRepository : CartRepository {

    private var products = mutableListOf<Product>()

    private var hasError: Boolean = false
    private var thrownError: Exception? = null

    fun expectError(
        throwError: Boolean = false,
        exception: Exception = Exception("Error occurred!")
    ) {
        hasError = throwError
        thrownError = exception
    }

    override suspend fun saveProduct(product: Product) {
        products.add(product)
    }

    override suspend fun getAllProducts(): Flow<List<Product>> {
        return flowOf(products)
    }

    override suspend fun getProduct(id: Int): Flow<Product?> {
        return flowOf(products.find { it.id == id })
    }

    override suspend fun deleteCartProduct(id: Int) {
        products.removeAll { it.id == id }
    }

    override suspend fun deleteAllProducts() {
        products.clear()
    }
}