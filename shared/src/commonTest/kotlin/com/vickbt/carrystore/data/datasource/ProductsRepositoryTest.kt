package com.vickbt.carrystore.data.datasource

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.vickbt.carrystore.data.datasources.ProductsRepositoryImpl
import com.vickbt.carrystore.data.network.FakeApiService
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class ProductsRepositoryTest {

    private lateinit var productsRepository: ProductsRepositoryImpl
    private lateinit var fakeApiService: FakeApiService

    @BeforeTest
    fun setup() {
        fakeApiService = FakeApiService()
        productsRepository = ProductsRepositoryImpl(fakeApiService)
    }

    @Test
    fun `fetchProducts returns list of products on success`() = runTest {
        val products = productsRepository.fetchProducts()

        products.test {
            assertThat(awaitItem().isSuccess)
            awaitComplete()
        }
    }

    @Test
    fun `fetchProducts returns error on failure`() = runTest {
        fakeApiService.expectError(throwError = true)

        val products = productsRepository.fetchProducts()

        products.test {
            assertThat(awaitItem().exceptionOrNull()?.message).isEqualTo("Error occurred!")
            awaitComplete()
        }
    }
}
