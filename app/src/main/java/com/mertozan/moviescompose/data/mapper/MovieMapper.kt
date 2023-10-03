package com.mertozan.moviescompose.data.mapper

import com.mertozan.moviescompose.data.model.dto.Movie
import com.mertozan.moviescompose.data.model.entity.MovieEntity
import com.mertozan.moviescompose.data.model.entity.TopMovieEntity
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.util.enums.ContentType
import com.mertozan.moviescompose.util.enums.ListType
import com.mertozan.moviescompose.util.extensions.orEmptyList
import com.mertozan.moviescompose.util.extensions.orFalse
import com.mertozan.moviescompose.util.extensions.orZero
import com.mertozan.moviescompose.util.extensions.orZeroFloat


fun MovieEntity.movieEntityToContentModel(): ContentModel {
    return ContentModel(
        id = id,
        title = title,
        popularity = popularity.toString(),
        posterPath = posterPath.orEmpty(),
        voteAverage = voteAverage.toString(),
        voteCount = voteCount.toString(),
        releaseDate = releaseDate.orEmpty(),
        adult = adult,
        runTime = runtime.toString(),
        isWatched = isWatched.orFalse(),
        isInWatchList = isInWatchList.orFalse(),
        type = ContentType.MOVIE.name,
        listType = ListType.POPULAR.name,
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
        releaseDate = releaseDate.orEmpty(),
        voteAverage = voteAverage.orZeroFloat().toString(),
        voteCount = voteCount.orZero().toString(),
        isWatched = isWatched.orFalse(),
        isInWatchList = isInWatchList.orFalse(),
        type = ContentType.MOVIE.name,
        listType = ListType.TOP_RATED.name,
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
            releaseDate = it.releaseDate.orEmpty(),
            posterPath = it.posterPath.orEmpty(),
            voteAverage = it.voteAverage.toString(),
            voteCount = it.voteCount.orZero().toString(),
            isWatched = it.isWatched.orFalse(),
            isInWatchList = it.isInWatchList.orFalse(),
            type = ContentType.MOVIE.name,
            listType = ListType.POPULAR.name,
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
            releaseDate = it.releaseDate.orEmpty(),
            posterPath = it.posterPath.orEmpty(),
            adult = it.adult,
            voteAverage = it.voteAverage.toString(),
            voteCount = it.voteCount.toString(),
            isFavorite = it.isFavorite,
            isWatched = it.isWatched.orFalse(),
            isInWatchList = it.isInWatchList.orFalse(),
            type = ContentType.MOVIE.name,
            listType = ListType.TOP_RATED.name,
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
            releaseDate = it.releaseDate.orEmpty(),
            posterPath = it.posterPath.orEmpty(),
            adult = it.adult,
            voteAverage = it.voteAverage.toFloat(),
            voteCount = it.voteCount.toInt(),
            isFavorite = it.isFavorite,
            isWatched = it.isWatched,
            type = it.type,
            listType = it.listType,
            isInWatchList = it.isInWatchList,
            originalLanguage = it.originalLanguage,
            overview = it.overview
        )
    }
}

fun List<ContentModel>.movieModelToTopMovieEntityList(): List<TopMovieEntity> {
    return map {
        TopMovieEntity(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toFloat(),
            releaseDate = it.releaseDate.orEmpty(),
            posterPath = it.posterPath.orEmpty(),
            adult = it.adult,
            voteAverage = it.voteAverage.toFloat(),
            voteCount = it.voteCount.toInt(),
            isFavorite = it.isFavorite,
            isWatched = it.isWatched,
            isInWatchList = it.isInWatchList,
            type = it.type,
            listType = it.listType,
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
            releaseDate = it.releaseDate.orEmpty(),
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

