package com.mertozan.moviescompose.domain.model

import com.mertozan.moviescompose.data.model.Genres

data class DetailItem(
    val id : Int = 0,
    val title: String = "",
    val popularity: String = "",
    val releaseDate: String = "",
    val genresDto: List<Genres>? = emptyList(),
    val posterPath: String? = "",
    val adult: Boolean = false,
    val runTime: String? = "",
    val isFavorite: Boolean = false,
    val originalLanguage: String = "",
    val overview: String = "No Detail"
)
