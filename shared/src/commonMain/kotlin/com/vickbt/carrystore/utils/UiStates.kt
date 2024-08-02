package com.vickbt.carrystore.utils

import com.vickbt.carrystore.domain.models.Product

data class ProductsUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val products: List<Product> = emptyList()
)