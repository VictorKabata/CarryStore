package com.vickbt.carrystore.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vickbt.carrystore.domain.models.Product
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

        composable(
            route = NavigationItem.ProductDetails.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("name") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("price") { type = NavType.IntType },
                navArgument("currencySymbol") { type = NavType.StringType },
                navArgument("currencyCode") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val product = Product(
                id = backStackEntry.arguments?.getInt("id") ?: 0,
                name = backStackEntry.arguments?.getString("name") ?: "",
                description = backStackEntry.arguments?.getString("description") ?: "",
                price = backStackEntry.arguments?.getInt("price") ?: 0,
                currencySymbol = backStackEntry.arguments?.getString("currencySymbol") ?: "",
                currencyCode = backStackEntry.arguments?.getString("currencyCode") ?: "",
                imageLocation = ""
            )

            ProductDetailsScreen(
                navHostController = navHostController,
                paddingValues = paddingValues,
                product = product
            )
        }

        composable(route = NavigationItem.Cart.route) {
            CartScreen(navHostController = navHostController, paddingValues = paddingValues)
        }
    }

}