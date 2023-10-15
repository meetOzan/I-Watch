package com.mertozan.moviescompose.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mertozan.moviescompose.presentation.theme.DarkWhite80
import com.mertozan.moviescompose.presentation.theme.DarkYellow
import com.mertozan.moviescompose.presentation.theme.LightBlack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertDialog(
    title: String,
    body: String,
    positiveButtonName : String,
    negativeButtonName : String,
    animation: String = "",
    onDismissClick: () -> Unit = {},
    onPositiveAction: () -> Unit = {},
    onNegativeAction : () -> Unit = {}
) {

    val splashAnimateComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.Url(animation)
    )

    AlertDialog(
        onDismissRequest = {
            onDismissClick()
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            color = LightBlack
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .background(LightBlack),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                if (animation != ""){
                    LottieAnimation(
                        composition = splashAnimateComposition,
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight(0.2f),
                        alignment = Alignment.Center
                    )
                }
                CustomText(
                    text = title,
                    color = DarkWhite80,
                    fontSize = 24,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomText(
                    text = body,
                    color = DarkWhite80,
                    fontSize = 20,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextButton(onClick = { onPositiveAction() }) {
                        CustomText(text = positiveButtonName, color = DarkYellow)
                    }
                    TextButton(onClick = { onNegativeAction()}) {
                        CustomText(text = negativeButtonName, color = DarkYellow)
                    }
                }
            }
        }
    }
}