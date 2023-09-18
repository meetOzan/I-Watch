package com.mertozan.moviescompose.data.mapper

import com.mertozan.moviescompose.data.model.dto.Series
import com.mertozan.moviescompose.data.model.entity.SeriesEntity
import com.mertozan.moviescompose.data.model.entity.TopSeriesEntity
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.util.enums.ContentType
import com.mertozan.moviescompose.util.enums.ListType
import com.mertozan.moviescompose.util.extensions.orEmptyList
import com.mertozan.moviescompose.util.extensions.orFalse
import com.mertozan.moviescompose.util.extensions.orZero
import com.mertozan.moviescompose.util.extensions.orZeroFloat

fun SeriesEntity.seriesEntityToDetailItem(): ContentModel {
    return ContentModel(
        id = id,
        title = title,
        popularity = popularity.toString(),
        posterPath = posterPath.orEmpty(),
        releaseDate = firstAirDate.orEmpty(),
        voteAverage = voteAverage.orZeroFloat().toString(),
        voteCount = voteCount.orZero().toString(),
        adult = adult.orFalse(),
        runTime = episodeNumber.orZero().toString(),
        originalLanguage = originalLanguage,
        overview = if (overview == "") "No Detail" else overview
    )
}

fun TopSeriesEntity.topSeriesEntityToDetailItem(): ContentModel {
    return ContentModel(
        id = id,
        title = title,
        popularity = popularity.toString(),
        posterPath = posterPath.orEmpty(),
        releaseDate = firstAirDate.orEmpty(),
        voteAverage = voteAverage.toString(),
        voteCount = voteCount.toString(),
        adult = adult,
        runTime = episodes.toString(),
        originalLanguage = originalLanguage,
        overview = if (overview == "") "No Detail" else overview
    )
}

fun List<Series>.seriesToSeriesModelList(): List<ContentModel> {
    return map {
        ContentModel(
            id = it.id,
            title = it.name,
            popularity = it.popularity.toString(),
            releaseDate = it.firstAirDate.orEmpty(),
            genresDto = it.genres.orEmptyList(),
            posterPath = it.posterPath,
            voteAverage = it.voteAverage.toString(),
            voteCount = it.voteNumber.toString(),
            adult = it.adult.orFalse(),
            runTime = it.episodeNumber.toString(),
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<SeriesEntity>.toSeriesMovieModelList(): List<ContentModel> {
    return map {
        ContentModel(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toString(),
            releaseDate = it.firstAirDate.orEmpty(),
            posterPath = it.posterPath.orEmpty(),
            adult = it.adult,
            voteAverage = it.voteAverage.toString(),
            voteCount = it.voteCount.toString(),
            isFavorite = it.isFavorite.orFalse(),
            isWatched = it.isWatched.orFalse(),
            isInWatchList = it.isInWatchList.orFalse(),
            type = ContentType.SERIES.name,
            listType = ListType.POPULAR.name,
            runTime = it.episodeNumber.toString(),
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<TopSeriesEntity>.toTopSeriesDetailItemList(): List<ContentModel> {
    return map {
        ContentModel(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toString(),
            releaseDate = it.firstAirDate,
            posterPath = it.posterPath.orEmpty(),
            adult = it.adult,
            isFavorite = it.isFavorite,
            voteAverage = it.voteAverage.toString(),
            voteCount = it.voteCount.toString(),
            isWatched = it.isWatched.orFalse(),
            isInWatchList = it.isInWatchList.orFalse(),
            type = ContentType.SERIES.name,
            listType = ListType.TOP_RATED.name,
            runTime = it.episodes.toString(),
            originalLanguage = it.originalLanguage,
            overview = it.overview,
        )
    }
}

fun List<ContentModel>.toDetailItemToSeriesEntityList(): List<SeriesEntity> {
    return map {
        SeriesEntity(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toFloat(),
            firstAirDate = it.releaseDate,
            posterPath = it.posterPath,
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

fun List<ContentModel>.toDetailItemToTopSeriesEntityList(): List<TopSeriesEntity> {
    return map {
        TopSeriesEntity(
            id = it.id,
            title = it.title,
            popularity = it.popularity.toFloat(),
            firstAirDate = it.releaseDate,
            posterPath = it.posterPath,
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