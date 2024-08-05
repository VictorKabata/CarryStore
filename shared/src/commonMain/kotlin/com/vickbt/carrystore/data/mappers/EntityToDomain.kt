package com.vickbt.carrystore.data.mappers

import com.vickbt.carrystore.data.cache.sqldelight.ProductEntity
import com.vickbt.carrystore.domain.models.Product

fun ProductEntity.toDomain(): Product {
    return Product(
        id = id.toInt(),
        name = name,
        description = description,
        price = price.toInt(),
        currencyCode = currencyCode,
        currencySymbol = currencySymbol,
        quantity = quantity.toInt(),
        imageLocation = imageLocation,
        status = status,
        cartQuantity = cartQuantity?.toInt(),
        createdAt = createdAt
    )
}