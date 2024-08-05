@file:OptIn(
    KoinExperimentalAPI::class,
    ExperimentalMaterial3Api::class
)

package com.vickbt.carrystore.ui.screens.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vickbt.carrystore.domain.models.Product
import com.vickbt.carrystore.ui.components.ErrorState
import com.vickbt.carrystore.ui.components.ItemProduct
import com.vickbt.carrystore.ui.screens.product_details.ProductBottomSheet
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ProductsScreen(
    navHostController: NavHostController,
    paddingValues: PaddingValues = PaddingValues(),
    viewModel: ProductsViewModel = koinViewModel<ProductsViewModel>()
) {

    val productsUiState = viewModel.products.collectAsState().value

    val localDensity = LocalDensity.current
    var sheetContentHeight by remember { mutableStateOf(Dp.Unspecified) }

    var selectedProduct by remember { mutableStateOf<Product?>(null) }

    val sheetState = rememberStandardBottomSheetState(
        initialValue = if (selectedProduct != null) SheetValue.Expanded else SheetValue.Hidden,
        skipHiddenState = false
    )
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    val scope = rememberCoroutineScope()

    println("Victor Height: $sheetContentHeight")

    Scaffold(modifier = Modifier.padding(paddingValues)){
        BottomSheetScaffold(
            // modifier = Modifier.padding(paddingValues),
            scaffoldState = bottomSheetScaffoldState,
            sheetShape = MaterialTheme.shapes.medium,
            sheetContainerColor = MaterialTheme.colorScheme.surface,
            sheetContent = {
                selectedProduct?.let {
                    ProductBottomSheet(
                        modifier = Modifier.fillMaxWidth().height(sheetContentHeight)
                            .onGloballyPositioned { coordinates ->
                                sheetContentHeight = with(localDensity) {
                                    if (sheetState.currentValue == SheetValue.Expanded) {
                                        coordinates.size.height.toDp()
                                    } else {
                                        Dp.Unspecified
                                    }
                                }
                            },
                        product = it,
                        onAddToCartClicked = {
                            viewModel.saveProduct(product = it)
                            scope.launch { sheetState.hide() }
                        },
                        onBuyNowClicked = {
                            scope.launch { sheetState.hide() }
                        }
                    )
                }
            }) {
            Box(modifier = Modifier.fillMaxSize()) {
                if (productsUiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else if (!productsUiState.errorMessage.isNullOrEmpty()) {
                    ErrorState(
                        modifier = Modifier.align(Alignment.Center),
                        errorIcon = Icons.Rounded.Person,
                        errorMessage = productsUiState.errorMessage,
                        actionMessage = "Reload"
                    )
                } else {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize().align(Alignment.Center),
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
                    ) {
                        items(productsUiState.products ?: emptyList()) { product ->
                            ItemProduct(modifier = Modifier, product = product) {
                                selectedProduct = product
                                scope.launch {
                                    if (!sheetState.isVisible) {
                                        sheetState.expand()
                                    } else {
                                        sheetState.hide()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}