package com.example.todo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Green500,
    onPrimary = Color.White,
    primaryVariant = Green300,
    background = Gray900,
    onBackground = Color.White,
    surface = Gray500,
    onSurface = Gray200,
    error = Red500
)

private val LightColorPalette = lightColors(
    primary = Green500,
    onPrimary = Color.White,
    primaryVariant = Green300,
    onBackground = Gray500,
    background = Gray200,
    surface = Color.White,
    onSurface = Gray500,
    error = Red500

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ToDoTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}