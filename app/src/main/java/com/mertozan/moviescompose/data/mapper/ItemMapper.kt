package com.mertozan.moviescompose.data.mapper

import com.mertozan.moviescompose.data.model.Movie
import com.mertozan.moviescompose.data.model.MovieEntity
import com.mertozan.moviescompose.data.model.Series
import com.mertozan.moviescompose.data.model.SeriesEntity
import com.mertozan.moviescompose.domain.model.DetailItem

fun Movie.toDetailItem(): DetailItem {
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

fun Series.toDetailItem(): DetailItem {
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

fun DetailItem.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        popularity = popularity.toFloat(),
        posterPath = posterPath,
        releaseDate = releaseDate,
        adult = adult,
        originalLanguage = originalLanguage,
        isFavorite = isFavorite,
        runtime = runTime.toInt(),
        overview = if (overview == "") "No Detail" else overview
    )
}

fun DetailItem.toSeriesEntity(): SeriesEntity {
    return SeriesEntity(
        id = id,
        title = title,
        popularity = popularity.toFloat(),
        posterPath = posterPath,
        firstAirDate = releaseDate,
        adult = adult,
        episodeNumber = runTime.toInt(),
        isFavorite = isFavorite,
        originalLanguage = originalLanguage,
        overview = if (overview == "") "No Detail" else overview
    )
}