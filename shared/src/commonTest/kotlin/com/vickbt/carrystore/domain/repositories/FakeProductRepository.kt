package com.vickbt.carrystore.domain.repositories

import com.vickbt.carrystore.domain.models.Product
import com.vickbt.carrystore.utils.ProductHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeProductRepository : ProductsRepository {

    private var hasError: Boolean = false
    private var thrownError: Exception? = null

    fun expectError(
        throwError: Boolean = false,
        exception: Exception = Exception("Error occurred!")
    ) {
        hasError = throwError
        thrownError = exception
    }

    override suspend fun fetchProducts(): Flow<Result<List<Product>>> = flow {
        val products = List(5) { ProductHelper.product }

        if (!hasError) Result.success(products)
        else Result.failure(Throwable(thrownError?.message))
    }
}