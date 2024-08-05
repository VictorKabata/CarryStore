package com.vickbt.carrystore.data.datasources

import com.vickbt.carrystore.data.cache.sqldelight.daos.CartDao
import com.vickbt.carrystore.data.mappers.toDomain
import com.vickbt.carrystore.domain.models.Product
import kotlinx.coroutines.flow.map

class CartRepository(private val cartDao: CartDao) {

    suspend fun saveProduct(product: Product) = cartDao.saveProduct(product)

    suspend fun getAllProducts() = cartDao.getAllProducts().map { it.map { it.toDomain() } }

    suspend fun getProduct(id: Int) = cartDao.getProduct(id).map { it?.toDomain() }

    suspend fun deleteCartProduct(id: Int) = cartDao.deleteProduct(id)

    suspend fun deleteAllProducts() = cartDao.deleteAllProducts()

}