@file:OptIn(KoinExperimentalAPI::class)

package com.vickbt.carrystore.ui.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            val subTotal = cartUiState.products.sumOf { it.price * (it.cartQuantity ?: 1) }
            val currencyCode =
                cartUiState.products.groupBy { it.currencyCode }.maxBy { it.value.size }.key

            Box(modifier = Modifier.fillMaxSize()) {

                LazyColumn(
                    modifier = Modifier.align(Alignment.TopCenter),
                    contentPadding = PaddingValues(horizontal = 12.dp)
                ) {
                    items(items = cartUiState.products) { product ->
                        ItemCartProduct(
                            modifier = Modifier.padding(vertical = 6.dp),
                            product = product,
                            onItemCountChanged = { itemCount ->
                                viewModel.saveProduct(product = product.copy(cartQuantity = itemCount))
                            },
                            onClickDelete = {
                                viewModel.deleteCartProduct(id = product.id)
                            }
                        )
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Column(modifier = Modifier.weight(5f)) {
                            Text(
                                text = "Total: ",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 1
                            )

                            Text(
                                text = "$currencyCode $subTotal",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 1
                            )
                        }

                        Button(
                            modifier = Modifier.weight(5f),
                            onClick = {
                                viewModel.deleteAllCartProducts()
                            },
                            shape = MaterialTheme.shapes.extraLarge
                        ) {
                            Text(
                                modifier = Modifier.padding(vertical = 6.dp),
                                text = "Checkout",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                    }
                }


            }
        }
    }

}