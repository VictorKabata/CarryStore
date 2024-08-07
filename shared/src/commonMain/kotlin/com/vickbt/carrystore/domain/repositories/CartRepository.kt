package com.vickbt.carrystore.domain.repositories

import com.vickbt.carrystore.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    suspend fun saveProduct(product: Product)

    suspend fun getAllProducts(): Flow<List<Product>>

    suspend fun getProduct(id: Int): Flow<Product?>

    suspend fun deleteCartProduct(id: Int)

    suspend fun deleteAllProducts()

}