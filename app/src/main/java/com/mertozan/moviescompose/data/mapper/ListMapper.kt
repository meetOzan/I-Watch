package com.mertozan.moviescompose.data.mapper

import com.mertozan.moviescompose.data.model.Movie
import com.mertozan.moviescompose.data.model.entity.MovieEntity
import com.mertozan.moviescompose.data.model.Series
import com.mertozan.moviescompose.data.model.entity.SeriesEntity
import com.mertozan.moviescompose.data.model.entity.TopMovieEntity
import com.mertozan.moviescompose.data.model.entity.TopSeriesEntity
import com.mertozan.moviescompose.domain.model.DetailItem

fun List<Movie>.moviesToDetailItemList(): List<DetailItem> {
    return this.map {
        DetailItem(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toString(),
            releaseDate = it.releaseDate,
            genresDto = it.genres,
            posterPath = it.posterPath.toString(),
            adult = it.adult,
            runTime = it.runtime.toString(),
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<Series>.seriesToDetailItemList(): List<DetailItem> {
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

fun List<MovieEntity>.toMoviesToDetailItemList(): List<DetailItem> {
    return this.map {
        DetailItem(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toString(),
            releaseDate = it.releaseDate,
            posterPath = it.posterPath.toString(),
            adult = it.adult,
            isFavorite = it.isFavorite,
            runTime = it.runtime.toString(),
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<SeriesEntity>.toSeriesDetailItemList(): List<DetailItem> {
    return this.map {
        DetailItem(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toString(),
            releaseDate = it.firstAirDate,
            posterPath = it.posterPath.toString(),
            adult = it.adult,
            isFavorite = it.isFavorite,
            runTime = it.episodeNumber.toString(),
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<TopMovieEntity>.toTopMoviesToDetailItemList(): List<DetailItem> {
    return this.map {
        DetailItem(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toString(),
            releaseDate = it.releaseDate,
            posterPath = it.posterPath.toString(),
            adult = it.adult,
            isFavorite = it.isFavorite,
            runTime = it.runtime.toString(),
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<TopSeriesEntity>.toTopSeriesDetailItemList(): List<DetailItem> {
    return this.map {
        DetailItem(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toString(),
            releaseDate = it.releaseDate,
            posterPath = it.posterPath.toString(),
            adult = it.adult,
            isFavorite = it.isFavorite,
            runTime = it.runtime.toString(),
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<DetailItem>.toDetailItemToMovieEntityList(): List<MovieEntity> {
    return this.map {
        MovieEntity(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toFloat(),
            releaseDate = it.releaseDate,
            posterPath = it.posterPath.toString(),
            adult = it.adult,
            isFavorite = it.isFavorite,
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<DetailItem>.toDetailItemToSeriesEntityList(): List<SeriesEntity> {
    return this.map {
        SeriesEntity(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toFloat(),
            firstAirDate = it.releaseDate,
            posterPath = it.posterPath.toString(),
            adult = it.adult,
            isFavorite = it.isFavorite,
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<DetailItem>.toDetailItemToTopMovieEntityList(): List<TopMovieEntity> {
    return this.map {
        TopMovieEntity(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toFloat(),
            releaseDate = it.releaseDate,
            posterPath = it.posterPath.toString(),
            adult = it.adult,
            isFavorite = it.isFavorite,
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<DetailItem>.toDetailItemToTopSeriesEntityList(): List<TopSeriesEntity> {
    return this.map {
        TopSeriesEntity(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toFloat(),
            releaseDate = it.releaseDate,
            posterPath = it.posterPath.toString(),
            adult = it.adult,
            isFavorite = it.isFavorite,
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}