package com.vickbt.carrystore.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("description")
    val description: String,

    @SerialName("price")
    val price: Int,

    @SerialName("currencyCode")
    val currencyCode: String,

    @SerialName("currencySymbol")
    val currencySymbol: String,

    @SerialName("quantity")
    val quantity: Int,

    @SerialName("imageLocation")
    val imageLocation: String,

    @SerialName("status")
    val status: String
)