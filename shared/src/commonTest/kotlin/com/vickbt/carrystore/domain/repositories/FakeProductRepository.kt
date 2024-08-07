package com.vickbt.carrystore.domain.repositories

import com.vickbt.carrystore.domain.models.Product
import com.vickbt.carrystore.utils.ProductHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

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

    override suspend fun fetchProducts(): Flow<Result<List<Product>>> {
        println("Has error: $hasError")

        val products = List(5) { ProductHelper.product }

        return if (!hasError) {
            flowOf(Result.success(products))
        } else {
            throw Throwable(thrownError?.message)
        }
    }
}
