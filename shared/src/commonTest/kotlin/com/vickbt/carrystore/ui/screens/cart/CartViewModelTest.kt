@file:OptIn(ExperimentalCoroutinesApi::class)

package com.vickbt.carrystore.ui.screens.cart

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import com.vickbt.carrystore.domain.repositories.FakeCartRepository
import com.vickbt.carrystore.utils.CartUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFails

class CartViewModelTest {

    // Subject under test
    private lateinit var viewModel: CartViewModel

    private val cartRepository = FakeCartRepository()

    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CartViewModel(cartRepository)
    }

    @AfterTest
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getAllProducts updates cart products on success`() = runTest {
        viewModel.cartUiState.test {
            assertThat(awaitItem()).apply {
                prop(CartUiState::isLoading).isFalse()
                prop(CartUiState::products).isNotNull()
                prop(CartUiState::errorMessage).isNull()
            }
        }
    }

    @Test
    fun `getAllProducts updates errorMessage on failure`() = runTest {
        cartRepository.shouldThrowError = true

        viewModel.cartUiState.test {
            assertFails("Error occurred!") {
                awaitError().message
            }
        }
    }
}
