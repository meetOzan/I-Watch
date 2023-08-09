package com.mertozan.moviescompose.domain.model

import com.mertozan.moviescompose.data.dto.GenresDto

data class MovieItem(
    val title: String,
    val popularity: Int,
    val adult: Boolean,
    val genresDto: GenresDto,
    val posterPath: String,
    val releaseDate: String,
    val overview: String
)
