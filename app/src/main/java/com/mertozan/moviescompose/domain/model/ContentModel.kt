package com.mertozan.moviescompose.domain.model

import com.mertozan.moviescompose.data.model.dto.Genres

data class ContentModel(
    val id : Int = 0,
    val title: String = "",
    val popularity: String = "",
    val releaseDate: String = "",
    val genresDto: List<Genres>? = emptyList(),
    val posterPath: String = "",
    val adult: Boolean = false,
    val voteAverage : String = "",
    val voteCount : String = "",
    val runTime: String = "",
    val isFavorite: Boolean = false,
    val isWatched: Boolean = false,
    val isInWatchList: Boolean = false,
    val type : String = "",
    val listType : String = "",
    val originalLanguage: String = "",
    val overview: String = "No Detail"
)
