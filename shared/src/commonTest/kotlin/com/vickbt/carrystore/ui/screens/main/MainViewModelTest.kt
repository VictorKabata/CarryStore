@file:OptIn(ExperimentalCoroutinesApi::class)

package com.vickbt.carrystore.ui.screens.main

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.prop
import com.vickbt.carrystore.domain.repositories.FakeCartRepository
import com.vickbt.carrystore.utils.MainUiState
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

    // Subject under test
    private lateinit var viewModel: MainScreenViewModel

    private var cartRepository = FakeCartRepository()

    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
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
            assertThat(awaitItem()).apply {
                prop(MainUiState::cartItemCount).isNotNull()
                prop(MainUiState::errorMessage).isEqualTo(null)
            }
        }
    }

    @Test
    fun `getAllProducts updates errorMessage on failure`() = runTest {
        cartRepository.shouldThrowError = true

        viewModel.getAllProducts()

        viewModel.mainUiState.test {
            assertThat(awaitItem()).apply {
                prop(MainUiState::cartItemCount).isEqualTo(0)
                prop(MainUiState::errorMessage).isEqualTo("Error occurred!")
            }
        }
    }
}
