package com.vickbt.carrystore.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import carrystore.shared.generated.resources.Res
import carrystore.shared.generated.resources.hello
import org.jetbrains.compose.resources.StringResource

sealed class NavigationItem(val route: String, val title: StringResource, val icon: ImageVector) {
    data object Products : NavigationItem("products", Res.string.hello, Icons.Rounded.Home)
    data object Cart : NavigationItem("cart", Res.string.hello, Icons.Rounded.ShoppingCart)
}