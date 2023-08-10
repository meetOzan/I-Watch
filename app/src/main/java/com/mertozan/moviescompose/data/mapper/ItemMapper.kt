package com.mertozan.moviescompose.data.mapper

import com.mertozan.moviescompose.data.model.Movie
import com.mertozan.moviescompose.data.model.Series
import com.mertozan.moviescompose.domain.model.MovieItem
import com.mertozan.moviescompose.domain.model.SeriesItem

fun Movie.toMovieItem(): MovieItem {
    return MovieItem(
        title = title,
        popularity = popularity,
        adult = adult,
        genresDto = genres,
        posterPath = posterPath,
        releaseDate = releaseDate,
        overview = overview
    )
}

fun Series.toSeriesItem(): SeriesItem {
    return SeriesItem(
        name = name,
        popularity = popularity,
        firstAirDate = firstAirDate,
        genresDto = genres,
        posterPath = posterPath,
        originalLanguage = originalLanguage,
        overview = overview
    )
}
