package com.mertozan.moviescompose.presentation.auth

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mertozan.moviescompose.domain.model.UserModel
import com.mertozan.moviescompose.presentation.auth.PagerState.PAGER_STATE
import com.mertozan.moviescompose.presentation.auth.components.SignInScreen
import com.mertozan.moviescompose.presentation.auth.components.SignUpScreen
import com.mertozan.moviescompose.presentation.auth.viewmodel.EntryAction
import com.mertozan.moviescompose.presentation.auth.viewmodel.EntryUiState
import com.mertozan.moviescompose.presentation.theme.LightBlack
import com.mertozan.moviescompose.util.enums.PagerScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    userModel: UserModel,
    authUiState: EntryUiState,
    onNavigate: () -> Unit,
    onAuthAction: (EntryAction) -> Unit
) {

    val pagerState = rememberPagerState(pageCount = { PAGER_STATE })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlack)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize(0.99f)
                .background(Color.DarkGray)
                .align(Alignment.Center)
        ) { page ->
            when (page) {
                PagerScreen.SIGN_IN.ordinal -> SignInScreen(
                    authUiState = authUiState,
                    onNavigate = onNavigate,
                    signInOnAction = onAuthAction
                )

                PagerScreen.SIGN_UP.ordinal -> SignUpScreen(
                    userModel = userModel,
                    onNavigate = onNavigate,
                    continueAction = onAuthAction
                )
            }
        }
    }
}

object PagerState {
    const val PAGER_STATE = 2
}
