package com.vickbt.carrystore.data.mappers

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.vickbt.carrystore.data.cache.sqldelight.ProductEntity
import kotlin.random.Random
import kotlin.test.Test

class EntityToDomainTest {

    @Test
    fun `entity to domain returns correct product`() {
        val productEntity = ProductEntity(
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

        val product = productEntity.toDomain()

        assertThat(product.id).isEqualTo(productEntity.id.toInt())
        assertThat(product.name).isEqualTo(productEntity.name)
    }

}