package com.mertozan.moviescompose.data.mapper

import com.mertozan.moviescompose.data.dto.MovieDto
import com.mertozan.moviescompose.data.dto.SeriesDto
import com.mertozan.moviescompose.domain.model.MovieItem
import com.mertozan.moviescompose.domain.model.SeriesItem

fun MovieDto.toMovieItem(): MovieItem {
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

fun SeriesDto.toSeriesItem(): SeriesItem {
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
