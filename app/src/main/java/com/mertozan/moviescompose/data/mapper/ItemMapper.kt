package com.mertozan.moviescompose.data.mapper

import com.mertozan.moviescompose.data.model.entity.MovieEntity
import com.mertozan.moviescompose.data.model.entity.SeriesEntity
import com.mertozan.moviescompose.data.model.entity.TopMovieEntity
import com.mertozan.moviescompose.data.model.entity.TopSeriesEntity
import com.mertozan.moviescompose.domain.model.DetailItem

fun MovieEntity.movieEntityToDetailItem(): DetailItem {
    return DetailItem(
        id = id,
        title = title,
        popularity = popularity.toString(),
        posterPath = posterPath,
        releaseDate = releaseDate,
        adult = adult,
        runTime = runtime.toString(),
        originalLanguage = originalLanguage,
        overview = if (overview == "") "No Detail" else overview
    )
}

fun SeriesEntity.seriesEntityToDetailItem(): DetailItem {
    return DetailItem(
        id = id,
        title = title,
        popularity = popularity.toString(),
        posterPath = posterPath,
        releaseDate = firstAirDate,
        adult = adult,
        runTime = episodeNumber.toString(),
        originalLanguage = originalLanguage,
        overview = if (overview == "") "No Detail" else overview
    )
}

fun TopMovieEntity.topMovieEntityToDetailItem(): DetailItem {
    return DetailItem(
        id = id,
        title = title,
        popularity = popularity.toString(),
        posterPath = posterPath,
        releaseDate = releaseDate,
        adult = adult,
        runTime = runtime.toString(),
        originalLanguage = originalLanguage,
        overview = if (overview == "") "No Detail" else overview
    )
}

fun TopSeriesEntity.topSeriesEntityToDetailItem(): DetailItem {
    return DetailItem(
        id = id,
        title = title,
        popularity = popularity.toString(),
        posterPath = posterPath,
        releaseDate = releaseDate,
        adult = adult,
        runTime = runtime.toString(),
        originalLanguage = originalLanguage,
        overview = if (overview == "") "No Detail" else overview
    )
}