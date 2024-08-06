package com.vickbt.carrystore.utils

import com.vickbt.carrystore.data.cache.sqldelight.ProductEntity
import com.vickbt.carrystore.data.network.models.ProductDto
import com.vickbt.carrystore.domain.models.Product
import kotlin.random.Random

object ProductHelper {

    val product
        get() = Product(
            id = Random.nextInt(),
            name = Random.nextInt().toString(),
            description = Random.nextInt().toString(),
            price = Random.nextDouble(),
            currencyCode = Random.nextInt().toString(),
            currencySymbol = Random.nextInt().toString(),
            quantity = Random.nextInt(),
            imageLocation = Random.nextInt().toString(),
            status = Random.nextInt().toString()
        )

    val productDto
        get() = ProductDto(
            id = Random.nextInt(),
            name = Random.nextInt().toString(),
            description = Random.nextInt().toString(),
            price = Random.nextInt(),
            currencyCode = Random.nextInt().toString(),
            currencySymbol = Random.nextInt().toString(),
            quantity = Random.nextInt(),
            imageLocation = Random.nextInt().toString(),
            status = Random.nextInt().toString()
        )

    val productEntity
        get() = ProductEntity(
            id = Random.nextLong(),
            name = Random.nextInt().toString(),
            description = Random.nextInt().toString(),
            price = Random.nextLong(),
            currencyCode = Random.nextInt().toString(),
            currencySymbol = Random.nextInt().toString(),
            quantity = Random.nextLong(),
            imageLocation = Random.nextInt().toString(),
            status = Random.nextInt().toString(),
            cartQuantity = Random.nextLong(),
            createdAt = Random.nextInt().toString()
        )
}
