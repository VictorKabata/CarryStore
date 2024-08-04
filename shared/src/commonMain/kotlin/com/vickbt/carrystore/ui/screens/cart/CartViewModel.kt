package com.vickbt.carrystore.ui.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickbt.carrystore.data.datasources.CartRepository
import com.vickbt.carrystore.utils.CartUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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

    fun deleteCartProduct(id: Int) = viewModelScope.launch {
        try {
            cartRepository.deleteCartProduct(id)
        } catch (e: Exception) {
            _cartUiState.value =
                _cartUiState.value.copy(isLoading = false, errorMessage = e.message)
        }
    }

}