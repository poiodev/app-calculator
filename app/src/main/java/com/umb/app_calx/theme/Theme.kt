package com.umb.app_calx.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DarkGray = Color(0xFF000000)
val LightYellow = Color(0xFFFFFF00)

@Composable
fun CalculatorTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = LightYellow,
            background = DarkGray
        ),
        content = content
    )
}