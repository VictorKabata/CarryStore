package com.vickbt.carrystore.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickbt.carrystore.domain.repositories.CartRepository
import com.vickbt.carrystore.utils.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainScreenViewModel(private val cartRepository: CartRepository) : ViewModel() {

    private val _mainUiState = MutableStateFlow(MainUiState())
    val mainUiState = _mainUiState.asStateFlow()

    fun getAllProducts() = viewModelScope.launch {
        try {
            cartRepository.getAllProducts().collectLatest { products ->
                _mainUiState.value = _mainUiState.value.copy(cartItemCount = products.size)
            }
        } catch (e: Exception) {
            _mainUiState.value =
                _mainUiState.value.copy(errorMessage = e.message)
        }
    }
}
