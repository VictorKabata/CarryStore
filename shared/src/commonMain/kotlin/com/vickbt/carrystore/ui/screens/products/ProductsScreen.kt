@file:OptIn(KoinExperimentalAPI::class, ExperimentalLayoutApi::class)

package com.vickbt.carrystore.ui.screens.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vickbt.carrystore.ui.components.ItemProduct
import com.vickbt.carrystore.ui.navigation.NavigationItem
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ProductsScreen(
    navHostController: NavHostController,
    viewModel: ProductsViewModel = koinViewModel<ProductsViewModel>()
) {

    println("Victor, ProductsScreen")
    val productsUiState = viewModel.products.collectAsState().value

    println("Victor Products in UI: ${productsUiState.products}")

    Box(modifier = Modifier.fillMaxSize()) {
        if (productsUiState.isLoading) {
            // ToDo: Show shimmer
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (!productsUiState.errorMessage.isNullOrEmpty()) {
            // ToDo: Show error message
        } else {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize().align(Alignment.Center),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
            ) {
                items(productsUiState.products ?: emptyList()) { product ->
                    ItemProduct(modifier = Modifier, product = product) {
                        navHostController.navigate(NavigationItem.Products.route)
                    }
                }
            }
        }
    }
}