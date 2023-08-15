package com.mertozan.moviescompose.domain.model

import com.mertozan.moviescompose.data.model.Genres

data class DetailItem(
    val title: String = "",
    val popularity: Float? = 0f,
    val releaseDate: String = "",
    val genresDto: List<Genres> = emptyList(),
    val posterPath: String = "",
    val adult: Boolean = false,
    val runTime: Int = 0,
    val originalLanguage: String = "",
    val overview: String = "No Detail"
)