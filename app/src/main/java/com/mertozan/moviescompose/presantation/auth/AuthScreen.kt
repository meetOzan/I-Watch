package com.mertozan.moviescompose.presantation.auth

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
import com.mertozan.moviescompose.domain.model.UserModel
import com.mertozan.moviescompose.presantation.auth.PagerState.PAGER_STATE
import com.mertozan.moviescompose.presantation.auth.signIn.SignInScreen
import com.mertozan.moviescompose.presantation.auth.signUp.SignUpScreen
import com.mertozan.moviescompose.presantation.auth.viewmodel.AuthAction
import com.mertozan.moviescompose.util.enums.PagerScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    userCurrent: Boolean,
    userModel: UserModel,
    errorMessage: String,
    onNavigate: () -> Unit,
    onAuthAction: (AuthAction) -> Unit
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
                PagerScreen.SIGN_IN.ordinal -> SignInScreen(
                    userModel = userModel,
                    userCurrent = userCurrent,
                    toastMessage = errorMessage,
                    onNavigate = onNavigate,
                    signInOnAction = onAuthAction
                )
                PagerScreen.SIGN_UP.ordinal -> SignUpScreen(
                    userModel = userModel,
                    userCurrent = userCurrent,
                    toastMessage = errorMessage,
                    onNavigate = onNavigate,
                    signUpOnAction = onAuthAction
                )
            }
        }
    }
}

object PagerState {
    const val PAGER_STATE = 2
}
