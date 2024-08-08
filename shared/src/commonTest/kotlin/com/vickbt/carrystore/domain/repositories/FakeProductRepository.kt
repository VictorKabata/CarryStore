package com.vickbt.carrystore.domain.repositories

import com.vickbt.carrystore.domain.models.Product
import com.vickbt.carrystore.utils.ProductHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeProductRepository : ProductsRepository {

    private var products = mutableListOf<Product>()

    var shouldThrowError = false

    override suspend fun fetchProducts(): Flow<Result<List<Product>>> = flow {
        products = MutableList(5) { ProductHelper.product }

        if (shouldThrowError) {
            throw Exception("Error occurred!")
        } else {
            emit(Result.success(products))
        }
    }
}
