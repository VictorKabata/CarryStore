@file:OptIn(KoinExperimentalAPI::class)

package com.vickbt.carrystore.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vickbt.carrystore.ui.components.AppBar
import com.vickbt.carrystore.ui.components.BottomNavBar
import com.vickbt.carrystore.ui.navigation.Navigation
import com.vickbt.carrystore.ui.navigation.NavigationItem
import com.vickbt.carrystore.ui.theme.CarryStoreTheme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun MainScreen(viewModel: MainScreenViewModel = koinViewModel<MainScreenViewModel>()) {
    CarryStoreTheme {
        val mainScreenUiState = viewModel.mainUiState.collectAsState().value

        val navHostController = rememberNavController()

        val currentDestination =
            navHostController.currentBackStackEntryAsState().value?.destination?.route

        val topLevelDestinations = listOf(NavigationItem.Products, NavigationItem.Cart)
        val isTopLevelDestination = currentDestination in topLevelDestinations.map { it.route }

        Scaffold(
            modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing),
            topBar = {
                if (isTopLevelDestination) {
                    AppBar(
                        title = stringResource(topLevelDestinations.find { it.route == currentDestination }!!.title)
                    )
                }
            },
            bottomBar = {
                if (isTopLevelDestination) {
                    BottomNavBar(
                        modifier = Modifier.fillMaxWidth(),
                        navHostController = navHostController,
                        cartItemCount = mainScreenUiState.cartItemCount,
                        bottomNavItems = topLevelDestinations
                    )
                }
            }
        ) { paddingValues ->
            Surface(modifier = Modifier.padding(paddingValues)) {
                Navigation(navHostController = navHostController)
            }
        }
    }
}
