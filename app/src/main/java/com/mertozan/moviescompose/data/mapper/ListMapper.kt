package com.mertozan.moviescompose.data.mapper

import com.mertozan.moviescompose.data.model.dto.Movie
import com.mertozan.moviescompose.data.model.dto.Series
import com.mertozan.moviescompose.data.model.entity.MovieEntity
import com.mertozan.moviescompose.data.model.entity.SeriesEntity
import com.mertozan.moviescompose.data.model.entity.TopMovieEntity
import com.mertozan.moviescompose.data.model.entity.TopSeriesEntity
import com.mertozan.moviescompose.domain.model.DetailItem
import com.mertozan.moviescompose.util.extensions.orEmptyList
import com.mertozan.moviescompose.util.extensions.orFalse
import com.mertozan.moviescompose.util.extensions.orZero

fun List<Movie>.moviesToDetailItemList(): List<DetailItem> {
    return map {
        DetailItem(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toString(),
            releaseDate = it.releaseDate,
            genresDto = it.genres.orEmptyList(),
            posterPath = it.posterPath.orEmpty(),
            voteAverage = it.voteAverage.toString(),
            voteCount = it.voteNumber.toString(),
            adult = it.adult,
            runTime = it.runtime.toString(),
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<Series>.seriesToDetailItemList(): List<DetailItem> {
    return map {
        DetailItem(
            id = it.id,
            title = it.name,
            popularity = it.popularity.toString(),
            releaseDate = it.firstAirDate,
            genresDto = it.genres.orEmptyList(),
            posterPath = it.posterPath.orEmpty(),
            voteAverage = it.voteAverage.toString(),
            voteCount = it.voteNumber.toString(),
            adult = it.adult.orFalse(),
            runTime = it.episodeNumber.toString(),
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<MovieEntity>.toMoviesToDetailItemList(): List<DetailItem> {
    return map {
        DetailItem(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toString(),
            releaseDate = it.releaseDate,
            posterPath = it.posterPath.orEmpty(),
            voteAverage = it.voteAverage.toString(),
            voteCount = it.voteCount.orZero().toString(),
            isFavorite = it.isFavorite,
            runTime = it.runtime.toString(),
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<SeriesEntity>.toSeriesDetailItemList(): List<DetailItem> {
    return map {
        DetailItem(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toString(),
            releaseDate = it.firstAirDate.orEmpty(),
            posterPath = it.posterPath.orEmpty(),
            adult = it.adult,
            voteAverage = it.voteAverage.toString(),
            voteCount = it.voteCount.toString(),
            isFavorite = it.isFavorite.orFalse(),
            runTime = it.episodeNumber.toString(),
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<TopMovieEntity>.toTopMoviesToDetailItemList(): List<DetailItem> {
    return map {
        DetailItem(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toString(),
            releaseDate = it.releaseDate,
            posterPath = it.posterPath.orEmpty(),
            adult = it.adult,
            voteAverage = it.voteAverage.toString(),
            voteCount = it.voteCount.toString(),
            isFavorite = it.isFavorite,
            runTime = it.runtime.toString(),
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<TopSeriesEntity>.toTopSeriesDetailItemList(): List<DetailItem> {
    return map {
        DetailItem(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toString(),
            releaseDate = it.releaseDate,
            posterPath = it.posterPath.orEmpty(),
            adult = it.adult,
            isFavorite = it.isFavorite,
            voteAverage = it.voteAverage.toString(),
            voteCount = it.voteCount.toString(),
            runTime = it.runtime.toString(),
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<DetailItem>.toDetailItemToMovieEntityList(): List<MovieEntity> {
    return map {
        MovieEntity(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toFloat(),
            releaseDate = it.releaseDate,
            posterPath = it.posterPath.orEmpty(),
            adult = it.adult,
            voteAverage = it.voteAverage.toFloat(),
            voteCount = it.voteCount.toInt(),
            isFavorite = it.isFavorite,
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<DetailItem>.toDetailItemToSeriesEntityList(): List<SeriesEntity> {
    return map {
        SeriesEntity(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toFloat(),
            firstAirDate = it.releaseDate,
            posterPath = it.posterPath.orEmpty(),
            adult = it.adult,
            voteAverage = it.voteAverage.toFloat(),
            voteCount = it.voteCount.toInt(),
            isFavorite = it.isFavorite,
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<DetailItem>.toDetailItemToTopMovieEntityList(): List<TopMovieEntity> {
    return map {
        TopMovieEntity(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toFloat(),
            releaseDate = it.releaseDate,
            posterPath = it.posterPath.orEmpty(),
            adult = it.adult,
            voteAverage = it.voteAverage.toFloat(),
            voteCount = it.voteCount.toInt(),
            isFavorite = it.isFavorite,
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<DetailItem>.toDetailItemToTopSeriesEntityList(): List<TopSeriesEntity> {
    return map {
        TopSeriesEntity(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toFloat(),
            releaseDate = it.releaseDate,
            posterPath = it.posterPath.orEmpty(),
            adult = it.adult,
            voteAverage = it.voteAverage.toFloat(),
            voteCount = it.voteCount.toInt(),
            isFavorite = it.isFavorite,
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}