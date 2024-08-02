package com.vickbt.carrystore.data.datasources

import com.vickbt.carrystore.data.network.ApiService
import com.vickbt.carrystore.domain.models.Product

internal class ProductsRepository(private val apiService: ApiService) {

    suspend fun fetchProducts(): Result<List<Product>> = apiService.fetchProducts()

}