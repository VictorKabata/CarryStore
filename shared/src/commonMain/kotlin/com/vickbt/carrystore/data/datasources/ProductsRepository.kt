package com.vickbt.carrystore.data.datasources

import com.vickbt.carrystore.data.network.ApiService
import com.vickbt.carrystore.data.network.ApiServiceImpl
import com.vickbt.carrystore.domain.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductsRepository(private val apiService: ApiService) {

    suspend fun fetchProducts(): Flow<Result<List<Product>>> = flowOf(apiService.fetchProducts())
}
