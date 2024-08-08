package com.vickbt.carrystore.data.datasources

import com.vickbt.carrystore.data.cache.sqldelight.daos.CartDao
import com.vickbt.carrystore.data.mappers.toDomain
import com.vickbt.carrystore.domain.models.Product
import com.vickbt.carrystore.domain.repositories.CartRepository
import kotlinx.coroutines.flow.map

class CartRepositoryImpl(private val cartDao: CartDao) : CartRepository {

    override suspend fun saveProduct(product: Product) = cartDao.saveProduct(product)

    override suspend fun getAllProducts() =
        cartDao.getAllProducts().map { it.map { product -> product.toDomain() } }

    override suspend fun getProduct(id: Int) = cartDao.getProduct(id).map { it?.toDomain() }

    override suspend fun deleteCartProduct(id: Int) = cartDao.deleteProduct(id)

    override suspend fun deleteAllProducts() = cartDao.deleteAllProducts()
}
