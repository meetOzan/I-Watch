package com.mertozan.moviescompose.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColors(
    primary = ThemeColors.DarkTheme.primaryColor,
    primaryVariant = ThemeColors.DarkTheme.iconColor,
    onPrimary = ThemeColors.DarkTheme.textColor,
    surface = ThemeColors.DarkTheme.surfaceColor,
    background = ThemeColors.DarkTheme.backgroundColor
)

private val LightColorScheme = darkColors(
    primary = ThemeColors.Day.primaryColor,
    primaryVariant = ThemeColors.Day.iconColor,
    onPrimary = ThemeColors.Day.textColor,
    surface = ThemeColors.Day.surfaceColor,
    background = ThemeColors.Day.backgroundColor
)

@Composable
fun MoviesComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    /*val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }*/

    androidx.compose.material.MaterialTheme(
        colors = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}