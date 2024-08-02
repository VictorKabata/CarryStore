package com.vickbt.carrystore.ui.screens.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickbt.carrystore.data.datasources.ProductsRepository
import com.vickbt.carrystore.domain.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductsViewModel(private val productsRepository: ProductsRepository) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    init {
        fetchProducts()
    }

    fun fetchProducts() = viewModelScope.launch {
        productsRepository.fetchProducts().collectLatest { result ->
            result.onSuccess { productsList ->
                _products.value = productsList
            }.onFailure {

            }
        }
    }

}