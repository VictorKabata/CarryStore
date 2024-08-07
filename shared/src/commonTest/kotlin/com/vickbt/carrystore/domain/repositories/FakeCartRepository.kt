package com.vickbt.carrystore.domain.repositories

import com.vickbt.carrystore.domain.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class FakeCartRepository : CartRepository {

    private var products = mutableListOf<Product>()

    var shouldThrowError = false

    override suspend fun saveProduct(product: Product) {
        if (shouldThrowError) {
            throw Exception("Error occurred!")
        } else {
            products.add(product)
        }
    }

    override suspend fun getAllProducts(): Flow<List<Product>> = flow {
        if (shouldThrowError) {
            throw Exception("Error occurred!")
        } else {
            emit(products)
        }
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
