package com.vickbt.carrystore.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vickbt.carrystore.ui.screens.cart.CartScreen
import com.vickbt.carrystore.ui.screens.product_details.ProductDetailsScreen
import com.vickbt.carrystore.ui.screens.products.ProductsScreen

@Composable
fun Navigation(
    navHostController: NavHostController,
    paddingValues: PaddingValues = PaddingValues()
) {

    NavHost(navController = navHostController, startDestination = NavigationItem.Products.route) {
        composable(route = NavigationItem.Products.route) {
            ProductsScreen(navHostController = navHostController, paddingValues = paddingValues)
        }

        composable(route = NavigationItem.ProductDetails.route) {
            ProductDetailsScreen(
                navHostController = navHostController,
                paddingValues = paddingValues
            )
        }

        composable(route = NavigationItem.Cart.route) {
            CartScreen(navHostController = navHostController, paddingValues = paddingValues)
        }
    }

}