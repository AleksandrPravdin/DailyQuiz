package com.example.dailyquiz.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = White,
    secondary = DarkGrey,
    tertiary = PurpleGrey,
    background = Purple,
    surface = Grey,
    onSurface = DarkPurple,
    onTertiary = Green,
    onSecondary = Red,
    onSurfaceVariant = Yellow
)

@Composable
fun DailyQuizTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}