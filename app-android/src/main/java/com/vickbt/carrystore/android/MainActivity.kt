package com.vickbt.carrystore.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vickbt.carrystore.ui.screens.main.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("Victor MainActivity")
        setContent {
            MainScreen()
        }
    }
}
