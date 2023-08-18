package com.mertozan.moviescompose.presantation.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.Black,
                        Color.Blue,
                        Color.Red,
                        Color.White,
                        Color.Yellow,
                        Color.Black
                    )
                )
            )
    ) {
        HorizontalPager(
            pageCount = 2,
            modifier = Modifier
                .fillMaxSize(0.96f)
                .background(Color.DarkGray)
                .align(Alignment.Center)
        ) { page ->
            SignUpScreen()
            SignInScreen()
        }
    }
}

@Preview
@Composable
fun PreviewLogin() {
    LoginScreen()
}