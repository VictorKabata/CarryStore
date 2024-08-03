@file:OptIn(KoinExperimentalAPI::class)

package com.vickbt.carrystore.ui.screens.product_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.vickbt.carrystore.domain.models.Product
import com.vickbt.carrystore.ui.screens.products.ProductsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ProductDetailsScreen(
    navHostController: NavHostController,
    paddingValues: PaddingValues = PaddingValues(),
    // product: Product,
    viewModel: ProductsViewModel = koinViewModel<ProductsViewModel>()
) {

    LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
        item {
            Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(.50f)) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = "https://dev-images-carry1st-products.s3.eu-west-2.amazonaws.com/a3fc8953-ba5b-4fb4-9017-223557716594.png",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                )

                IconButton(
                    modifier = Modifier.align(Alignment.TopStart).padding(16.dp),
                    onClick = { navHostController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }

            }
        }
    }

}