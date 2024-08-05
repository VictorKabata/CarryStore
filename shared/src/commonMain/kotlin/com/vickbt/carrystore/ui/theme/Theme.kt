@file:OptIn(ExperimentalCoilApi::class)

package com.vickbt.carrystore.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import com.vickbt.carrystore.utils.getAsyncImageLoader

private val DarkColorPalette = darkColorScheme(
    primary = DarkPrimaryColor,
    onPrimary = Color(0xFFFFFFFF),
    secondary = DarkPrimaryColor,
    onSecondary = DarkTextSecondary,
    surface = DarkSurface,
    onSurface = DarkTextPrimary,
    background = DarkSurface
)

private val LightColorPalette = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = Color(0xFF000000),
    secondary = PrimaryColor,
    onSecondary = TextSecondary,
    surface = Surface,
    onSurface = TextPrimary,
    background = Surface
)

@Composable
fun CarryStoreTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorPalette else LightColorPalette

    setSingletonImageLoaderFactory { context ->
        context.getAsyncImageLoader()
    }

    MaterialTheme(
        colorScheme = colorScheme,
        // typography = Typography,
        shapes = Shapes
    ) {
        content()
    }
}
