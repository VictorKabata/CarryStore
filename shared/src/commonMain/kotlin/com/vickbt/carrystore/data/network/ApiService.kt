package com.vickbt.carrystore.data.network

import com.vickbt.carrystore.data.mappers.toDomain
import com.vickbt.carrystore.data.network.NetworkClient.httpClient
import com.vickbt.carrystore.data.network.models.ProductDto
import com.vickbt.carrystore.domain.models.Product
import com.vickbt.carrystore.utils.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

class ApiService(private val httpClient: HttpClient) {

    suspend fun fetchProducts(): Result<List<Product>> = safeApiCall {
        val response = httpClient.get(urlString = "productBundle").body<List<ProductDto>>()
        return@safeApiCall response.map { it.toDomain() }
    }

}