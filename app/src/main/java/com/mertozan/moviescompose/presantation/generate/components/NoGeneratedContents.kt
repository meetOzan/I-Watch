package com.mertozan.moviescompose.presantation.generate.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.presantation.main_components.CustomText
import com.mertozan.moviescompose.presantation.theme.DarkWhite80

@Composable
fun NoGeneratedContents() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.6f)
    ) {
        Image(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = stringResource(R.string.play_arrow),
            colorFilter = ColorFilter.tint(DarkWhite80),
            modifier = Modifier
                .size(120.dp)
                .alpha(0.7f)
        )
        CustomText(
            text = stringResource(R.string.click_button_to_prefer),
            fontSize = 18,
            modifier = Modifier.padding(start = 8.dp, top = 12.dp)
        )
    }
}