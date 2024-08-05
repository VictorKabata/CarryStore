package com.vickbt.carrystore.ui.screens.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickbt.carrystore.data.datasources.CartRepository
import com.vickbt.carrystore.utils.ProductDetailsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailsViewModel(private val cartRepository: CartRepository) : ViewModel() {

    private val _productDetailsUiState = MutableStateFlow(ProductDetailsUiState())
    val productDetailsUiState = _productDetailsUiState.asStateFlow()

    fun getProduct(id: Int) = viewModelScope.launch {
        try {
            cartRepository.getProduct(id).collectLatest { product ->
                _productDetailsUiState.update { it.copy(product = product) }
            }
        } catch (e: Exception) {
            _productDetailsUiState.update { it.copy(errorMessage = e.message) }
        }
    }

}