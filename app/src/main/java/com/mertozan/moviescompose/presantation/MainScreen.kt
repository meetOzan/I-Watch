package com.mertozan.moviescompose.presantation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.data.model.Movie
import com.mertozan.moviescompose.data.model.Series
import com.mertozan.moviescompose.ui.theme.LightBlack
import com.mertozan.moviescompose.ui.theme.amazonEmberFamily

@Composable
fun MainScreen(
    movieList: List<Movie>,
    seriesList: List<Series>
) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Divider(
                modifier = Modifier
                    .height(25.dp)
                    .width(3.5.dp),
                color = Color.Yellow
            )
            Text(
                text = stringResource(R.string.top_20_movies_on_this_week),
                fontFamily = amazonEmberFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }

        LazyRow(
            modifier = Modifier
                .background(LightBlack)
        ) {
            items(movieList) { movie ->
                MovieItem(
                    onCardClick = {},
                    posterPath = movie.posterPath,
                    title = movie.title,
                    number = (movieList.indexOf(movie)) + 1
                )
            }
        }

        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Divider(
                modifier = Modifier
                    .height(25.dp)
                    .width(3.5.dp),
                color = Color.Yellow
            )
            Text(
                text = stringResource(R.string.top_20_tv_series_on_this_week),
                fontFamily = amazonEmberFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }
        LazyRow(
            modifier = Modifier
                .background(LightBlack)
        ) {
            items(seriesList) { series ->
                MovieItem(
                    onCardClick = {},
                    posterPath = series.posterPath,
                    title = series.name,
                    number = (seriesList.indexOf(series)) + 1
                )
            }
        }
    }
}