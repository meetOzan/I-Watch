package com.mertozan.moviescompose.presantation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.data.model.Movie
import com.mertozan.moviescompose.data.model.Series
import com.mertozan.moviescompose.presantation.viewmodel.DetailViewModel
import com.mertozan.moviescompose.ui.theme.amazonEmberFamily

@Composable
fun DetailScreen(
    onBackClicked: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel(),
    id: Int?,
    type: String?
) {

    var isFavorite by rememberSaveable {
        mutableStateOf(false)
    }

    val animateFavColor: Color by animateColorAsState(
        if (isFavorite) Color.Red else Color.White,
        label = stringResource(R.string.animated_color)
    )

    var series: Series? = null
    var movies: Movie? = null

    if (type == DataTypes.SERIES.toString()) {
        viewModel.getSingleSeries(3)
        series = viewModel.singleSeries.collectAsState().value
    } else {
        viewModel.getSingleMovie(4)
        movies = viewModel.singleMovie.collectAsState().value
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = (stringResource(
                    R.string.https_image_tmdb_org_t_p_original
                )) + (series?.posterPath ?: movies?.posterPath)!!,
                contentDescription = stringResource(R.string.movie_poster),
                modifier = Modifier
                    .padding(bottom = 2.dp)
                    .fillMaxSize(0.8f),
                alignment = Alignment.Center
            )
            Image(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back_arrow),
                colorFilter = ColorFilter.tint(Color.White),
                alignment = Alignment.TopStart,
                modifier = Modifier
                    .padding(8.dp)
                    .size(36.dp)
                    .clickable(onClick = onBackClicked),
            )
            Image(
                imageVector = Icons.Filled.Favorite,
                contentDescription = stringResource(R.string.add_fav),
                alignment = Alignment.TopEnd,
                colorFilter = ColorFilter.tint(animateFavColor),
                modifier = Modifier
                    .padding(8.dp)
                    .size(36.dp)
                    .clickable { isFavorite = !isFavorite },
            )
        }
        Text(
            // TODO Title
            text = "BladeRunner 2049",
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = amazonEmberFamily,
            fontWeight = FontWeight.Bold
        )
        Text(
            // TODO Popularity, Circle Score
            text = "64.56",
            fontSize = 16.sp,
            color = Color.White,
            fontFamily = amazonEmberFamily,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.border(width = 0.2.dp, color = Color.White),
            ) {
                Text(
                    text = stringResource(R.string.duration),
                    fontSize = 12.sp,
                    color = Color.White,
                    fontFamily = amazonEmberFamily,
                )
                Text(
                    // TODO runtime
                    text = "2h19m",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontFamily = amazonEmberFamily,
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.border(width = 0.2.dp, color = Color.White),
            ) {
                Text(
                    text = stringResource(R.string.adult),
                    fontSize = 12.sp,
                    color = Color.White,
                    fontFamily = amazonEmberFamily,
                )
                Image(
                    imageVector = Icons.Filled.CheckCircle,
                    colorFilter = ColorFilter.tint(Color.Green),
                    contentDescription = stringResource(R.string.adult_yes),
                )
                /*Image(
                    imageVector = Icons.Filled.AddCircle,
                    modifier = Modifier.rotate(90f),
                    colorFilter = ColorFilter.tint(Color.Red),
                    contentDescription = stringResource(R.string.adult_yes),
                )*/
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.border(width = 0.2.dp, color = Color.White),
            ) {
                Text(
                    text = stringResource(R.string.language),
                    fontSize = 12.sp,
                    color = Color.White,
                    fontFamily = amazonEmberFamily,
                )
                Text(
                    // TODO Language
                    text = "EN",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontFamily = amazonEmberFamily,
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.border(width = 0.2.dp, color = Color.White),
            ) {
                Text(
                    text = "Release Date",
                    fontSize = 12.sp,
                    color = Color.White,
                    fontFamily = amazonEmberFamily,
                )
                Text(
                    // TODO Release Date
                    text = "2019-22-07",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontFamily = amazonEmberFamily,
                )
            }
        }
        Text(
            //TODO Description
            text = "Description",
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
            color = Color.White,
            fontFamily = amazonEmberFamily,
        )
    }
}