package com.vickbt.carrystore.data.mappers

import com.vickbt.carrystore.data.network.models.ProductDto
import com.vickbt.carrystore.domain.models.Product

fun ProductDto.toDomain(): Product {
    return Product(
        id = this.id,
        name = this.name,
        description = this.description,
        price = this.price.toDouble(),
        currencyCode = this.currencyCode,
        currencySymbol = this.currencySymbol,
        quantity = this.quantity,
        imageLocation = this.imageLocation,
        status = this.status
    )
}