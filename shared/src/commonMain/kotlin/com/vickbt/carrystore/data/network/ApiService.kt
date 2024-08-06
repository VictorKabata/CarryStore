package com.vickbt.carrystore.data.network

import com.vickbt.carrystore.domain.models.Product

interface ApiService {

    suspend fun fetchProducts(): Result<List<Product>>

}