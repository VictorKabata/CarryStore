package com.vickbt.carrystore.ui.screens.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickbt.carrystore.data.datasources.ProductsRepository
import com.vickbt.carrystore.utils.ProductsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductsViewModel(private val productsRepository: ProductsRepository) : ViewModel() {

    private val _products = MutableStateFlow(ProductsUiState())
    val products = _products.asStateFlow()

    init {
        fetchProducts()
        println("Invoked VM fetchProducts")
    }

    fun fetchProducts() = viewModelScope.launch {
        productsRepository.fetchProducts().collectLatest { result ->
            result.onSuccess { productsList ->
                println("Victor Product list: $productsList")
                _products.update { it.copy(products = productsList) }
            }.onFailure { error ->
                println("Victor Failed: ${error.message}")
                _products.update { it.copy(errorMessage = error.message) }
            }
        }
    }

}