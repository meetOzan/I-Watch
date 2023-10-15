package com.mertozan.moviescompose.presentation.generate

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mathroda.snackie.rememberSnackieState
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.infrastructure.connectivity.ConnectivityObserver
import com.mertozan.moviescompose.infrastructure.connectivity.NetworkConnectivityObserver
import com.mertozan.moviescompose.presentation.generate.components.NoGeneratedContents
import com.mertozan.moviescompose.presentation.generate.viewmodel.GenerateAction
import com.mertozan.moviescompose.presentation.generate.viewmodel.GenerateUiState
import com.mertozan.moviescompose.presentation.main.components.ConnectivityAlertDialog
import com.mertozan.moviescompose.presentation.main.components.CustomAsyncImage
import com.mertozan.moviescompose.presentation.main.components.CustomText
import com.mertozan.moviescompose.presentation.theme.DarkWhite80
import com.mertozan.moviescompose.presentation.theme.DarkYellow
import com.mertozan.moviescompose.presentation.theme.LightBlack
import com.mertozan.moviescompose.util.extensions.isLongerThan

@Composable
fun GenerateContent(
    trendList: List<ContentModel>,
    generateUiState: GenerateUiState,
    onGenerateAction: (GenerateAction) -> Unit,
    onOkClicked: () -> Unit
) {

    val context = LocalContext.current


    // Connectivity

    val connectivityObserver: ConnectivityObserver = NetworkConnectivityObserver(context)

    val status by connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable
    )

    var isPreferred by remember {
        mutableStateOf(false)
    }

    val splashAnimateComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.Url(stringResource(R.string.lottie_generate_loading))
    )

    var openDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        if (openDialog) {
            ConnectivityAlertDialog(
                title = status.name,
                onDismissClick = { openDialog = !openDialog },
                onOkClicked = { onOkClicked() }
            )
        }

        Box(
            modifier = Modifier.padding(top = 50.dp)
        ) {
            if (isPreferred) {
                if (generateUiState.isLoading) {
                    LottieAnimation(
                        composition = splashAnimateComposition,
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .fillMaxHeight(0.6f),
                        alignment = Alignment.Center
                    )
                } else {
                    CustomAsyncImage(
                        model = trendList[0].posterPath,
                        contentDescription = stringResource(
                            R.string.generated_image
                        ),
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .fillMaxHeight(0.5f)
                            .background(LightBlack),
                        alignment = Alignment.Center
                    )
                }
            } else {
                NoGeneratedContents()
            }
        }
        AnimatedVisibility(visible = isPreferred) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 12.dp)
            ) {
                CustomText(
                    text = trendList[0].title.isLongerThan(30),
                    color = DarkWhite80,
                    fontSize = 24,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Button(
                    onClick = {
                        onGenerateAction(
                            GenerateAction.AddToWatchList(
                                id = trendList[0].id,
                                isInWatchList = trendList[0].isInWatchList,
                                type = trendList[0].type,
                                listType = trendList[0].listType
                            )
                        )
                        if (!trendList[0].isInWatchList) {
                            Toast.makeText(
                                context,
                                context.getText(R.string.removed_from_watch_list),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                context.getText(R.string.removed_from_watch_list),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(vertical = 36.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = DarkWhite80
                    ),
                ) {
                    Text(text = stringResource(R.string.add_to_your_watch_list), color = LightBlack)
                }
            }
        }
        IconButton(
            onClick = {
                if (status != ConnectivityObserver.Status.Available) {
                    openDialog = !openDialog
                } else {
                    if (!isPreferred) {
                        isPreferred = true
                    } else {
                        onGenerateAction(GenerateAction.ShuffledList)
                    }
                }
            }, modifier = Modifier
                .size(
                    64.dp
                )
                .clip(CircleShape),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = DarkYellow
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = stringResource(R.string.generate_button),
                tint = Color.Black,
                modifier = Modifier
                    .size(32.dp)
                    .graphicsLayer(
                        scaleX = if (isPreferred) 1.1f else 1.0f,
                        scaleY = if (isPreferred) 1.1f else 1.0f
                    )
            )
        }
    }
}