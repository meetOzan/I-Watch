package com.mertozan.moviescompose.data.mapper

import com.mertozan.moviescompose.data.model.Movie
import com.mertozan.moviescompose.data.model.Series
import com.mertozan.moviescompose.domain.model.DetailItem

fun Movie.toMovieItem(): DetailItem {
    return DetailItem(
        id = id,
        title = title,
        popularity = popularity.toString(),
        genresDto = genres,
        posterPath = posterPath,
        releaseDate = releaseDate,
        adult = adult,
        runTime = runtime.toString(),
        originalLanguage = originalLanguage,
        overview = if (overview == "") "No Detail" else overview
    )
}

fun Series.toSeriesItem(): DetailItem {
    return DetailItem(
        id = id,
        title = name,
        popularity = popularity.toString(),
        genresDto = genres,
        posterPath = posterPath,
        releaseDate = firstAirDate,
        adult = adult,
        runTime = episodeNumber.toString(),
        originalLanguage = originalLanguage,
        overview = if (overview == "") "No Detail" else overview
    )
}
