package com.vickbt.carrystore.ui.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vickbt.carrystore.ui.components.AppBar
import com.vickbt.carrystore.ui.components.BottomNavBar
import com.vickbt.carrystore.ui.navigation.Navigation
import com.vickbt.carrystore.ui.navigation.NavigationItem
import com.vickbt.carrystore.ui.theme.CarryStoreTheme

@Composable
fun MainScreen() {

    CarryStoreTheme {
        val navHostController = rememberNavController()

        val topLevelDestinations = listOf(NavigationItem.Products, NavigationItem.Cart)
        val isTopLevelDestination =
            navHostController.currentBackStackEntryAsState().value?.destination?.route in topLevelDestinations.map { it.route }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                if (isTopLevelDestination) {
                    AppBar(
                        title = navHostController.currentBackStackEntryAsState().value?.destination?.route?.capitalize(
                            Locale.current
                        )
                            ?: ""
                    )
                }
            },
            bottomBar = {
                if (isTopLevelDestination) {
                    BottomNavBar(
                        modifier = Modifier.fillMaxWidth(),
                        navHostController = navHostController,
                        bottomNavItems = topLevelDestinations
                    )
                }
            }) { paddingValues ->
            Navigation(navHostController = navHostController, paddingValues = paddingValues)
        }
    }
}