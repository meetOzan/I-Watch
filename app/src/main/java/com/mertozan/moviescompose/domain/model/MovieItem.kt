package com.mertozan.moviescompose.domain.model

import com.mertozan.moviescompose.data.model.Genres

data class MovieItem(
    val title: String,
    val popularity: Int,
    val adult: Boolean,
    val genresDto: Genres,
    val posterPath: String,
    val releaseDate: String,
    val overview: String
)
