package com.vickbt.carrystore.data.network

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.vickbt.carrystore.data.mappers.toDomain
import com.vickbt.carrystore.data.network.models.ProductDto
import kotlin.random.Random
import kotlin.test.Test

class DtoToDomainTest {

    @Test
    fun `dto to domain returns correct product`() {
        val productDto = ProductDto(
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

        val product = productDto.toDomain()

        assertThat(product.id).isEqualTo(productDto.id)
        assertThat(product.name).isEqualTo(productDto.name)
    }

}