package com.mertozan.moviescompose.data.mapper

import com.mertozan.moviescompose.data.model.Movie
import com.mertozan.moviescompose.data.model.Series
import com.mertozan.moviescompose.domain.model.DetailItem

fun Movie.toMovieItem(): DetailItem {
    return DetailItem(
        title = title,
        popularity = popularity,
        genresDto = genres,
        posterPath = posterPath,
        releaseDate = releaseDate,
        adult = adult,
        runTime = runtime,
        originalLanguage = originalLanguage,
        overview = overview
    )
}

fun Series.toSeriesItem(): DetailItem {
    return DetailItem(
        title = name,
        popularity = popularity,
        genresDto = genres,
        posterPath = posterPath,
        releaseDate = firstAirDate,
        adult = adult,
        runTime = episodeNumber,
        originalLanguage = originalLanguage,
        overview = overview
    )
}
