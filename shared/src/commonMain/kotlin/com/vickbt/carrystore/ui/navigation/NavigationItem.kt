package com.vickbt.carrystore.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val route: String, val title: String, val icon: ImageVector?) {
    object Products : NavigationItem("products", "Products", Icons.Rounded.Home)
    object ProductDetails : NavigationItem("product", "Product Details", null)
    object Cart : NavigationItem("cart", "Cart", Icons.Rounded.ShoppingCart)
}