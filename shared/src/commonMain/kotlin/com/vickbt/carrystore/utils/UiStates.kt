package com.vickbt.carrystore.utils

import com.vickbt.carrystore.domain.models.Product

data class MainUiState(val cartItemCount: Int = 0, val errorMessage: String? = null)

data class ProductsUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val products: List<Product>? = emptyList()
)

data class ProductDetailsUiState(
    val errorMessage: String? = null,
    val product: Product? = null,
)

data class CartUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val products: List<Product>? = emptyList()
)