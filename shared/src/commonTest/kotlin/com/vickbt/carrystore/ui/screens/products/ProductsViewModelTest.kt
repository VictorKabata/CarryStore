@file:OptIn(ExperimentalCoroutinesApi::class)

package com.vickbt.carrystore.ui.screens.products

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import com.vickbt.carrystore.domain.repositories.FakeCartRepository
import com.vickbt.carrystore.domain.repositories.FakeProductRepository
import com.vickbt.carrystore.utils.ProductHelper
import com.vickbt.carrystore.utils.ProductsUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFails

class ProductsViewModelTest {

    // Subject under test
    private lateinit var viewModel: ProductsViewModel

    private val productsRepository = FakeProductRepository()
    private val cartRepository = FakeCartRepository()

    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ProductsViewModel(productsRepository, cartRepository)
    }

    @AfterTest
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getAllProducts updates products on success`() = runTest(testDispatcher) {
        viewModel.productsUiState.test {
            assertThat(awaitItem()).apply {
                prop(ProductsUiState::isLoading).isFalse()
                prop(ProductsUiState::products).isNotNull()
                prop(ProductsUiState::errorMessage).isNull()
            }
        }
    }

    @Test
    fun `fetchProducts updates errorMessage on failure`() = runBlocking {
        productsRepository.shouldThrowError = true

        viewModel.productsUiState.test {
            assertFails("Error occurred!") {
                awaitError().message
            }
        }
    }

    @Test
    fun `saveProduct saves correct product`() = runBlocking {
        val product = ProductHelper.product

        viewModel.saveProduct(product = product)

        viewModel.fetchProducts()

        viewModel.productsUiState.test {
            assertThat(awaitItem()).apply {
                prop(ProductsUiState::isLoading).isFalse()
                prop(ProductsUiState::products).isNotNull()
                prop(ProductsUiState::errorMessage).isNull()
            }
        }
    }

    @Test
    fun `saveProduct updates errorMessage on failure`() = runBlocking {
        val product = ProductHelper.product

        cartRepository.shouldThrowError = true

        viewModel.saveProduct(product = product)

        viewModel.productsUiState.test {
            assertFails("Error occurred!") { awaitError().message }
        }
    }
}
