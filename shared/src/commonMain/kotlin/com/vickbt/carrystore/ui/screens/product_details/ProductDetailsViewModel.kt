package com.vickbt.carrystore.ui.screens.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickbt.carrystore.data.datasources.CartRepository
import com.vickbt.carrystore.domain.models.Product
import com.vickbt.carrystore.utils.ProductDetailsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailsViewModel(private val cartRepository: CartRepository) : ViewModel() {

    private val _productDetailsUiState = MutableStateFlow(ProductDetailsUiState())
    val productDetailsUiState = _productDetailsUiState.asStateFlow()

    fun isProductInCart(id: Int) = viewModelScope.launch {
        cartRepository.isProductInCart(id = id).collectLatest {
            _productDetailsUiState.update { it.copy(isProductInCart = it.isProductInCart) }
        }
    }

    fun saveProduct(product: Product) = viewModelScope.launch {
        try {
            cartRepository.saveProduct(product = product)
        } catch (e: Exception) {
            _productDetailsUiState.update { it.copy(errorMessage = e.message) }
        }
    }
}