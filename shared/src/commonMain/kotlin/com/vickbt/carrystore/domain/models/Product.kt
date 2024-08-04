package com.vickbt.carrystore.domain.models

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val currencyCode: String,
    val currencySymbol: String,
    val quantity: Int? = null,
    val imageLocation: String,
    val status: String? = null
)