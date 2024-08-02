package com.vickbt.carrystore.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.vickbt.carrystore.di.commonModule
import com.vickbt.carrystore.di.platformModule
import org.koin.compose.KoinApplication

private val DarkColorPalette = darkColorScheme(
    primary = DarkPrimaryColor,
    secondary = DarkPrimaryColor,
    surface = DarkSurface,
    onSurface = DarkTextPrimary,
    background = DarkSurface
)

private val LightColorPalette = lightColorScheme(
    primary = PrimaryColor,
    secondary = PrimaryColor,
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

    KoinApplication(application = {
        modules(commonModule, platformModule())
    }) {
        MaterialTheme(
            colorScheme = colorScheme,
            // typography = Typography,
            shapes = Shapes
        ) {
            content()
        }
    }
}
