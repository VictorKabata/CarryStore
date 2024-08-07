package com.vickbt.carrystore.data.datasources

import com.vickbt.carrystore.data.network.ApiService
import com.vickbt.carrystore.domain.models.Product
import com.vickbt.carrystore.domain.repositories.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductsRepositoryImpl(private val apiService: ApiService) : ProductsRepository {

    override suspend fun fetchProducts(): Flow<Result<List<Product>>> =
        flowOf(apiService.fetchProducts())
}
