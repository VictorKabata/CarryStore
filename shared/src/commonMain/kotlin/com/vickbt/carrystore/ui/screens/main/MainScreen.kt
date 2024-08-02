package com.vickbt.carrystore.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.vickbt.carrystore.ui.navigation.Navigation
import com.vickbt.carrystore.ui.theme.CarryStoreTheme
import io.github.aakira.napier.Napier

@Composable
fun MainScreen() {
    CarryStoreTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            val navHostController = rememberNavController()

            Navigation(navHostController = navHostController)
        }
    }
}