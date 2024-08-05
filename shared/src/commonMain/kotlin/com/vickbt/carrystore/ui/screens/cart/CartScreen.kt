@file:OptIn(KoinExperimentalAPI::class)

package com.vickbt.carrystore.ui.screens.cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vickbt.carrystore.ui.components.ErrorState
import com.vickbt.carrystore.ui.components.ItemCartProduct
import com.vickbt.carrystore.ui.navigation.NavigationItem
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun CartScreen(
    navHostController: NavHostController,
    paddingValues: PaddingValues = PaddingValues(),
    viewModel: CartViewModel = koinViewModel<CartViewModel>()
) {

    val cartUiState = viewModel.cartUiState.collectAsState().value

    Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {

        if (cartUiState.isLoading) {
            CircularProgressIndicator()
        } else if (!cartUiState.errorMessage.isNullOrEmpty()) {
            ErrorState(
                modifier = Modifier,
                errorIcon = Icons.Rounded.Person,
                errorMessage = cartUiState.errorMessage,
                actionMessage = "Reload"
            )
        } else if (cartUiState.products.isNullOrEmpty()) {
            ErrorState(
                modifier = Modifier,
                errorIcon = Icons.Rounded.ShoppingCart,
                errorMessage = "Cart is empty",
                actionMessage = "Shop Now"
            ) {
                navHostController.navigate(NavigationItem.Products.route)
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp)) {
                items(items = cartUiState.products) { product ->
                    ItemCartProduct(
                        modifier = Modifier.padding(vertical = 4.dp),
                        product = product,
                        onClickDelete = { viewModel.deleteCartProduct(id = product.id) },
                        onClick = {}
                    )
                }
            }
        }
    }

}