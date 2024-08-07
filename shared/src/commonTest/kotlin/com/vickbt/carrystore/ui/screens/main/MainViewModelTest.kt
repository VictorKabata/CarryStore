@file:OptIn(ExperimentalCoroutinesApi::class)

package com.vickbt.carrystore.ui.screens.main

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.vickbt.carrystore.domain.repositories.CartRepository
import com.vickbt.carrystore.domain.repositories.FakeCartRepository
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

class MainViewModelTest {

    private lateinit var viewModel: MainScreenViewModel
    private lateinit var cartRepository: CartRepository

    @BeforeTest
    fun setup() {
        val testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)

        cartRepository = FakeCartRepository()
        viewModel = MainScreenViewModel(cartRepository)
    }

    @AfterTest
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getAllProducts updates cartItemCount on success`() = runTest {
        viewModel.getAllProducts()
        viewModel.mainUiState.test {
            assertThat(awaitItem().cartItemCount).isEqualTo(0)
        }

        val products = List(5) { ProductHelper.product }
        products.forEach { cartRepository.saveProduct(product = it) }

        viewModel.getAllProducts()
        viewModel.mainUiState.test {
            assertThat(awaitItem().cartItemCount).isEqualTo(products.size)
        }
    }

    @Test
    fun `getAllProducts updates errorMessage on failure`() = runTest {
        //cartRepository.expectError(throwError = true)
        viewModel.getAllProducts()

        viewModel.mainUiState.test {
            assertThat(awaitItem().errorMessage).isEqualTo("Error occurred!")
        }
    }

}
