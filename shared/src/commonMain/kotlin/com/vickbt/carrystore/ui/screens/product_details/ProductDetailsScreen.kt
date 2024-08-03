@file:OptIn(KoinExperimentalAPI::class)

package com.vickbt.carrystore.ui.screens.product_details

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.vickbt.carrystore.ui.screens.products.ProductsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ProductDetailsScreen(
    navHostController: NavHostController,
    paddingValues: PaddingValues = PaddingValues(),
    viewModel: ProductsViewModel = koinViewModel<ProductsViewModel>()
) {
}