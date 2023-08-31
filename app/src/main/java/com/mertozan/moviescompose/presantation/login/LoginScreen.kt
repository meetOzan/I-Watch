package com.mertozan.moviescompose.presantation.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.mertozan.moviescompose.common.Constants.PAGER_STATE

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    onNavigate: () -> Unit,
    viewModel: LoginViewModel,
    context : Context
) {
    val pagerState = rememberPagerState(pageCount = { PAGER_STATE })

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
            state = pagerState,
            modifier = Modifier
                .fillMaxSize(0.96f)
                .background(Color.DarkGray)
                .align(Alignment.Center)
        ) { page ->
            when (page) {
                0 -> SignInScreen(onNavigate, viewModel,context)
                1 -> SignUpScreen(onNavigate, viewModel,context)
            }
        }
    }
}