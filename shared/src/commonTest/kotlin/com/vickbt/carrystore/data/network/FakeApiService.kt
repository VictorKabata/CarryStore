package com.vickbt.carrystore.data.network

import com.vickbt.carrystore.domain.models.Product
import com.vickbt.carrystore.utils.ProductHelper

class FakeApiService : ApiService {

    private var hasError: Boolean = false
    private var thrownError: Exception = Exception("Error occurred")

    fun expectError(
        throwError: Boolean = false,
        exception: Exception = Exception("Error occurred!")
    ) {
        hasError = throwError
        thrownError = exception
    }

    override suspend fun fetchProducts(): Result<List<Product>> {
        val products = listOf(ProductHelper.product, ProductHelper.product, ProductHelper.product)

        return if (hasError) {
            Result.failure(thrownError)
        } else {
            Result.success(products)
        }
    }
}