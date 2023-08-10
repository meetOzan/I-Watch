package com.mertozan.moviescompose.domain.model

import com.mertozan.moviescompose.data.model.Genres

data class MovieItem(
    val title: String? = null,
    val popularity: Float? = null,
    val adult: Boolean? = null,
    val genresDto: Genres? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val overview: String? = null
)
