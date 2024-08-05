package com.vickbt.carrystore.domain.models

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val currencyCode: String,
    val currencySymbol: String,
    val quantity: Int,
    val imageLocation: String,
    val status: String,
    val cartQuantity: Int? = null,
    val createdAt: String? = null
)