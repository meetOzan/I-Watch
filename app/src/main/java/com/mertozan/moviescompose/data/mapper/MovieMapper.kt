package com.mertozan.moviescompose.data.mapper

import com.mertozan.moviescompose.data.model.dto.Movie
import com.mertozan.moviescompose.data.model.entity.MovieEntity
import com.mertozan.moviescompose.data.model.entity.SeriesEntity
import com.mertozan.moviescompose.data.model.entity.TopMovieEntity
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.util.extensions.orEmptyList
import com.mertozan.moviescompose.util.extensions.orFalse
import com.mertozan.moviescompose.util.extensions.orZero
import com.mertozan.moviescompose.util.extensions.orZeroFloat


fun MovieEntity.movieEntityToContentModel(): ContentModel {
    return ContentModel(
        id = id,
        title = title,
        popularity = popularity.toString(),
        posterPath = posterPath,
        voteAverage = voteAverage.toString(),
        voteCount = voteCount.toString(),
        releaseDate = releaseDate,
        adult = adult,
        runTime = runtime.toString(),
        originalLanguage = originalLanguage,
        overview = if (overview == "") "No Detail" else overview
    )
}

fun TopMovieEntity.topMovieEntityToMovieModel(): ContentModel {
    return ContentModel(
        id = id,
        title = title,
        popularity = popularity.toString(),
        posterPath = posterPath.orEmpty(),
        releaseDate = releaseDate,
        voteAverage = voteAverage.orZeroFloat().toString(),
        voteCount = voteCount.orZero().toString(),
        adult = adult.orFalse(),
        runTime = runtime.orZero().toString(),
        originalLanguage = originalLanguage,
        overview = if (overview == "") "No Detail" else overview
    )
}

fun List<MovieEntity>.moviesToMoviesModelList(): List<ContentModel> {
    return map {
        ContentModel(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toString(),
            releaseDate = it.releaseDate,
            posterPath = it.posterPath,
            voteAverage = it.voteAverage.toString(),
            voteCount = it.voteCount.orZero().toString(),
            isFavorite = it.isFavorite,
            runTime = it.runtime.toString(),
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<TopMovieEntity>.topMoviesToMovieModelList(): List<ContentModel> {
    return map {
        ContentModel(
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

fun List<ContentModel>.movieModelToMovieEntityList(): List<MovieEntity> {
    return map {
        MovieEntity(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toFloat(),
            releaseDate = it.releaseDate,
            posterPath = it.posterPath,
            adult = it.adult,
            voteAverage = it.voteAverage.toFloat(),
            voteCount = it.voteCount.toInt(),
            isFavorite = it.isFavorite,
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<ContentModel>.movieModelToTopMovieEntityList(): List<TopMovieEntity> {
    return map {
        TopMovieEntity(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toFloat(),
            releaseDate = it.releaseDate,
            posterPath = it.posterPath,
            adult = it.adult,
            voteAverage = it.voteAverage.toFloat(),
            voteCount = it.voteCount.toInt(),
            isFavorite = it.isFavorite,
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<Movie>.moviesToMovieModelList(): List<ContentModel> {
    return map {
        ContentModel(
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

