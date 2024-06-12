package com.example.instagramclone.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Blue,
    secondary = Purple,
    tertiary = Red,
    background = Black,
    surface = Black,
    onBackground = White,
    onSurface = White,
    onPrimary = White,
    onSecondary = White,
    onTertiary = White,
    error = Red,
    outline = DarkGray
)

private val LightColorScheme = lightColorScheme(
    primary = Blue,
    secondary = Purple,
    tertiary = Red,
    background = White,
    surface = White,
    onBackground = Black,
    onPrimary = White,
    onSurface = Black,
    onSecondary = Black,
    onTertiary = Black,
    error = Red,
    outline = DarkGray
)

@Composable
fun InstagramCloneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
        shapes = Shapes
    )
}