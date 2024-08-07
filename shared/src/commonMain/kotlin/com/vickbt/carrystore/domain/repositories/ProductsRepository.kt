package com.vickbt.carrystore.domain.repositories

import com.vickbt.carrystore.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun fetchProducts(): Flow<Result<List<Product>>>

}