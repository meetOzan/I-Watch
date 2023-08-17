package com.mertozan.moviescompose.data.mapper

import com.mertozan.moviescompose.data.model.Movie
import com.mertozan.moviescompose.data.model.Series
import com.mertozan.moviescompose.domain.model.DetailItem

fun List<Movie>.moviesToList(): List<DetailItem> {
    return this.map {
        DetailItem(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toString(),
            releaseDate = it.releaseDate,
            genresDto = it.genres,
            posterPath = it.posterPath,
            adult = it.adult,
            runTime = it.runtime.toString(),
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<Series>.seriesToList(): List<DetailItem> {
    return this.map {
        DetailItem(
            id = it.id,
            title = it.name,
            popularity = it.popularity.toString(),
            releaseDate = it.firstAirDate,
            genresDto = it.genres,
            posterPath = it.posterPath,
            adult = it.adult,
            runTime = it.episodeNumber.toString(),
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}