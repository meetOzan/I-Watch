package com.mertozan.moviescompose.domain.model

import com.mertozan.moviescompose.data.model.Genres

data class SeriesItem(
    val name: String,
    val popularity: Int,
    val firstAirDate: String,
    val genresDto: Genres,
    val posterPath: String,
    val originalLanguage: String,
    val overview: String
)