@file:OptIn(KoinExperimentalAPI::class)

package com.vickbt.carrystore.ui.screens.cart

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun CartScreen(
    navHostController: NavHostController,
    paddingValues: PaddingValues = PaddingValues(),
    viewModel: CartViewModel = koinViewModel<CartViewModel>()
) {

}