package com.mertozan.moviescompose.presantation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.presantation.main_components.CustomText
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashNavigate: () -> Unit) {

    val scale = remember {
        Animatable(0f)
    }

    val splashAnimateComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.Url(stringResource(R.string.lottie_loading_link))
    )

    LaunchedEffect(key1 = Unit) {
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(2500)
        onSplashNavigate()
    }

    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(1.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painterResource(id = R.drawable.splash_logo),
                contentDescription = stringResource(R.string.splash_icon),
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(150.dp)
            )
            CustomText(
                text = stringResource(R.string.app_name),
                color = Color.White,
                fontSize = 36,
                modifier = Modifier.padding(top = 20.dp),
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LottieAnimation(
                composition = splashAnimateComposition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .size(72.dp)
                    .padding(bottom = 8.dp)
            )

            CustomText(
                text = stringResource(R.string.made_by_meetozan),
                color = Color.White,
                fontSize = 12,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}