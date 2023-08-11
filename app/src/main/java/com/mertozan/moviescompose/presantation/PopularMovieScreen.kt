package com.mertozan.moviescompose.presantation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MoviesList(
    viewModel: MovieViewModel = hiltViewModel()
) {

    val movieList = viewModel.popularMovies.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        LazyColumn(
            modifier = Modifier,
            content = {
                items(movieList) { movie ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 8.dp,
                                horizontal = 24.dp
                            )
                            .border(
                                width = 1.dp,
                                shape = MaterialTheme.shapes.large,
                                brush = Brush.linearGradient(
                                    listOf(
                                        Color.Blue,
                                        Color.Yellow
                                    )
                                )
                            )
                    ) {
                        Text(text = movie.title)
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrevMovieList() {
    MoviesList()
}