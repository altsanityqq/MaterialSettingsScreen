package com.example.materialsettingsscreen.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val CustomColorScheme = lightColorScheme(
    primary = GreenPrimary,
    onPrimary = Color.White,
    background = BackgroundLight,
    onBackground = TextPrimary,
    surface = Color.White,
    onSurface = TextPrimary
)

object MaterialSettingsScreenTheme {
    val colorScheme: ColorScheme
        @Composable
        get() = MaterialTheme.colorScheme

    val typography: Typography
        @Composable
        get() = MaterialTheme.typography
}


@Composable
fun MaterialSettingsScreenTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = CustomColorScheme,
        typography = Typography,
        content = content
    )
}
