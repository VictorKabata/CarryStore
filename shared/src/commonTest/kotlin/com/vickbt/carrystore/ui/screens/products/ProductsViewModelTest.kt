@file:OptIn(ExperimentalCoroutinesApi::class)

package com.vickbt.carrystore.ui.screens.products

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNull
import assertk.assertions.isNullOrEmpty
import assertk.assertions.isTrue
import com.vickbt.carrystore.domain.repositories.FakeCartRepository
import com.vickbt.carrystore.domain.repositories.FakeProductRepository
import com.vickbt.carrystore.utils.ProductHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class ProductsViewModelTest {

    private lateinit var viewModel: ProductsViewModel
    private lateinit var productsRepository: FakeProductRepository
    private lateinit var cartRepository: FakeCartRepository

    @BeforeTest
    fun setup() {
        val testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)

        productsRepository = FakeProductRepository()
        cartRepository = FakeCartRepository()
        viewModel = ProductsViewModel(productsRepository, cartRepository)
    }

    @AfterTest
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchProducts updates products on success`() = runTest {
        viewModel.fetchProducts()
        viewModel.products.test {
            assertThat(awaitItem().isLoading).isTrue()
            assertThat(awaitItem().products).isNullOrEmpty()
            awaitComplete()
        }

        val products = List(5) { ProductHelper.product }
        products.forEach { cartRepository.saveProduct(product = it) }

        viewModel.fetchProducts()
        viewModel.products.test {
            assertThat(awaitItem().isLoading).isFalse()
            assertThat(awaitItem().products).isEqualTo(products)
            assertThat(awaitItem().errorMessage).isNull()
            awaitComplete()
        }
    }

}
