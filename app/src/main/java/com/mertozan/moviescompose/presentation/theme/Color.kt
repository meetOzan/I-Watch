package com.mertozan.moviescompose.presentation.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Dark80 = Color(0xFF303030)
val LightBlack = Color(0xFF181818)
val DarkYellow = Color(0xFFFFC61D)
val LightGray70 = Color(0xFF5E5E5E)
val DarkRed80 = Color(0xFFB10000)

val DarkWhite80 = Color(0xFFDAD7D7)
val DarkWhite70 = Color(0xFFA7A7A7)
val DarkWhite60 = Color(0xFF818181)

sealed class ThemeColors(
    val backgroundColor: Color,
    val surfaceColor: Color,
    val primaryColor: Color,
    val iconColor: Color,
    val textColor: Color
){

    data object Day : ThemeColors(
        backgroundColor = DarkWhite80,
        surfaceColor = DarkWhite70,
        primaryColor = DarkWhite60,
        iconColor = DarkYellow,
        textColor = Color.Black
    )

    data object DarkTheme : ThemeColors(
        backgroundColor = Color.Black,
        surfaceColor = LightBlack,
        primaryColor = Dark80,
        iconColor = DarkYellow,
        textColor = DarkWhite80
    )

}