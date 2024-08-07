package com.vickbt.carrystore.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vickbt.carrystore.ui.screens.cart.CartScreen
import com.vickbt.carrystore.ui.screens.products.ProductsScreen

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = NavigationItem.Products.route) {
        composable(route = NavigationItem.Products.route) {
            ProductsScreen()
        }

        composable(route = NavigationItem.Cart.route) {
            CartScreen(navHostController = navHostController)
        }
    }
}
