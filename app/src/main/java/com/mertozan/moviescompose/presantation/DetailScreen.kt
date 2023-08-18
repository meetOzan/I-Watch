package com.mertozan.moviescompose.presantation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mertozan.moviescompose.BuildConfig
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.presantation.viewmodel.DetailViewModel
import com.mertozan.moviescompose.ui.theme.amazonEmberFamily

@Composable
fun DetailScreen(
    onBackClicked: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel(),
) {

    var isFavorite by rememberSaveable {
        mutableStateOf(false)
    }

    val animateFavColor: Color by animateColorAsState(
        if (isFavorite) Color.Yellow else Color.White,
        label = stringResource(R.string.animated_color)
    )

    val detail = viewModel.movieDetailUiState.collectAsState().value

    LaunchedEffect(Unit){
        viewModel.getList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = "${BuildConfig.POSTER_BASE_PATH}${detail.posterPath}",
                contentDescription = stringResource(R.string.movie_poster),
                modifier = Modifier
                    .padding(bottom = 2.dp)
                    .fillMaxSize(1f),
                alignment = Alignment.Center,
            )
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black
                            )
                        )
                    )
                    .fillMaxWidth()
                    .height(200.dp)
                    .align(Alignment.BottomCenter)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
                    .background(Color.Black, shape = MaterialTheme.shapes.medium)
                    .alpha(0.5f)
            ) {
                Image(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_arrow),
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .padding(4.dp)
                        .size(36.dp)
                        .clickable(onClick = onBackClicked),
                )
            }

        }
        Text(
            text = detail.title,
            fontSize = 22.sp,
            modifier = Modifier.padding(vertical = 8.dp),
            color = Color.White,
            fontFamily = amazonEmberFamily,
            fontWeight = FontWeight.Bold
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(280.dp)
        ) {
            Text(
                text = detail.popularity,
                fontSize = 16.sp,
                color = Color.White,
                fontFamily = amazonEmberFamily,
            )

            Image(
                imageVector = Icons.Filled.Favorite,
                contentDescription = stringResource(R.string.add_fav),
                colorFilter = ColorFilter.tint(animateFavColor),
                modifier = Modifier
                    .size(24.dp)
                    .clickable { isFavorite = !isFavorite },
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.duration),
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                    color = Color.White,
                    fontFamily = amazonEmberFamily,
                )
                Text(
                    text = detail.runTime,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontFamily = amazonEmberFamily,
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.adult),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 7.dp),
                    color = Color.White,
                    fontFamily = amazonEmberFamily,
                )
                if (detail.adult) {
                    Image(
                        imageVector = Icons.Filled.CheckCircle,
                        colorFilter = ColorFilter.tint(Color.Green),
                        modifier = Modifier
                            .size(20.dp)
                            .padding(top = 6.dp, start = 6.dp),
                        contentDescription = stringResource(R.string.adult_yes),
                    )
                } else {
                    Image(
                        imageVector = Icons.Filled.AddCircle,
                        modifier = Modifier
                            .rotate(45f)
                            .padding(top = 6.dp, start = 6.dp)
                            .size(20.dp),
                        colorFilter = ColorFilter.tint(Color.Red),
                        contentDescription = stringResource(R.string.no_adult),
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.language),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                    color = Color.White,
                    fontFamily = amazonEmberFamily,
                )
                Text(
                    text = detail.originalLanguage.uppercase(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontFamily = amazonEmberFamily,
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.release_date),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                    color = Color.White,
                    fontFamily = amazonEmberFamily,
                )
                Text(
                    text = detail.releaseDate,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontFamily = amazonEmberFamily,
                )
            }
        }
        Text(
            text = stringResource(R.string.details),
            modifier = Modifier
                .padding(
                    top = 16.dp
                ),
            fontSize = 24.sp,
            color = Color.White,
            fontFamily = amazonEmberFamily,
        )
        Text(
            text = detail.overview,
            modifier = Modifier.padding(
                horizontal = 24.dp,
                vertical = 8.dp
            ),
            color = Color.White,
            fontFamily = amazonEmberFamily,
        )
    }
}