package com.vickbt.carrystore.ui.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickbt.carrystore.data.datasources.CartRepository
import com.vickbt.carrystore.domain.models.Product
import com.vickbt.carrystore.utils.CartUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(private val cartRepository: CartRepository) : ViewModel() {

    private val _cartUiState = MutableStateFlow(CartUiState())
    val cartUiState = _cartUiState.asStateFlow()

    init {
        getAllProducts()
    }

    fun getAllProducts() = viewModelScope.launch {
        try {
            cartRepository.getAllProducts().collectLatest {
                _cartUiState.value = _cartUiState.value.copy(isLoading = false, products = it)
            }
        } catch (e: Exception) {
            _cartUiState.value =
                _cartUiState.value.copy(isLoading = false, errorMessage = e.message)
        }
    }

    fun deleteAllCartProducts() = viewModelScope.launch {
        try {
            cartRepository.deleteAllProducts()
        } catch (e: Exception) {
            _cartUiState.value =
                _cartUiState.value.copy(isLoading = false, errorMessage = e.message)
        }
    }

    fun deleteCartProduct(id: Int) = viewModelScope.launch {
        try {
            cartRepository.deleteCartProduct(id)
        } catch (e: Exception) {
            _cartUiState.value =
                _cartUiState.value.copy(isLoading = false, errorMessage = e.message)
        }
    }

    fun saveProduct(product: Product) = viewModelScope.launch {
        try {
            cartRepository.saveProduct(product = product)
        } catch (e: Exception) {
            _cartUiState.update { it.copy(errorMessage = e.message) }
        }
    }

}