@file:OptIn(KoinExperimentalAPI::class)

package com.vickbt.carrystore.ui.screens.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ProductsScreen(
    navHostController: NavHostController,
    viewModel: ProductsViewModel = koinViewModel<ProductsViewModel>()
) {

    val productsUiState = viewModel.products.collectAsState().value

    println("Victor ProductsScreen")
    println("Victor Products: ${productsUiState.products}")
    println("Victor Is loading: ${productsUiState.isLoading}")
    println("Victor Error: ${productsUiState.errorMessage}")

    Box(modifier = Modifier.background(Color.Red))

}