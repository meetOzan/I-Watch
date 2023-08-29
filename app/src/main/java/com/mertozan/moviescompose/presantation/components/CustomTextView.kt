package com.mertozan.moviescompose.presantation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mertozan.moviescompose.ui.theme.DarkWhite80
import com.mertozan.moviescompose.ui.theme.amazonEmberFamily

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int = 16,
    color: Color = DarkWhite80,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = text,
        fontSize = fontSize.sp,
        fontFamily = amazonEmberFamily,
        color = color,
        fontWeight = fontWeight,
        modifier = modifier
    )
}