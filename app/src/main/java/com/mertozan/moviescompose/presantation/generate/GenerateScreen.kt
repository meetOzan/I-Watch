package com.mertozan.moviescompose.presantation.generate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.presantation.components.components.CustomAsyncImage
import com.mertozan.moviescompose.presantation.components.components.CustomText
import com.mertozan.moviescompose.ui.theme.DarkWhite80
import com.mertozan.moviescompose.ui.theme.LightBlack

@Composable
fun GenerateContent(
    viewModel: GenerateViewModel,
    navController: NavController
) {

    var isPreferred by remember {
        mutableStateOf(false)
    }

    val trendList = viewModel.allContents.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.padding(top = 50.dp)
        ) {
            if (isPreferred) {
                CustomAsyncImage(
                    model = trendList[0].posterPath.toString(),
                    contentDescription = stringResource(
                        R.string.generated_image
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.6f)
                        .background(LightBlack),
                    // TODO clickable eklemesi için tabloların tek tablo olması lazım
                    alignment = Alignment.Center
                )
            } else {
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
        }
        AnimatedVisibility(visible = isPreferred) {
            Button(
                onClick = {}, modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 36.dp)
            ) {
                Text(text = stringResource(R.string.add_to_your_watch_list))
            }
        }
        IconButton(
            onClick = {
                if (!isPreferred) {
                    isPreferred = true
                } else {
                    viewModel.shuffleList()
                }
            }, modifier = Modifier
                .size(75.dp)
                .clip(CircleShape),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.Yellow
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = stringResource(R.string.generate_button),
                tint = Color.Black,
                modifier = Modifier.size(36.dp)
            )
        }
    }
}