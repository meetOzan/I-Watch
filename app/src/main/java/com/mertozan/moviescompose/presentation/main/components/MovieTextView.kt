package com.mertozan.moviescompose.presentation.main.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mertozan.moviescompose.presentation.theme.DarkWhite80
import com.mertozan.moviescompose.presentation.theme.amazonEmberFamily

@Composable
fun CustomText(
    text: String,
    fontSize: Int = 16,
    color: Color = DarkWhite80,
    fontWeight: FontWeight = FontWeight.Normal,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = fontSize.sp,
        fontFamily = amazonEmberFamily,
        color = color,
        fontWeight = fontWeight,
        modifier = modifier,
    )
}