package com.mertozan.moviescompose.data.mapper

import com.mertozan.moviescompose.data.model.User
import com.mertozan.moviescompose.data.model.entity.MovieEntity
import com.mertozan.moviescompose.data.model.entity.SeriesEntity
import com.mertozan.moviescompose.data.model.entity.TopMovieEntity
import com.mertozan.moviescompose.data.model.entity.TopSeriesEntity
import com.mertozan.moviescompose.data.model.entity.UserEntity
import com.mertozan.moviescompose.domain.model.DetailItem
import com.mertozan.moviescompose.domain.model.UserItem
import com.mertozan.moviescompose.util.extensions.orFalse
import com.mertozan.moviescompose.util.extensions.orZero
import com.mertozan.moviescompose.util.extensions.orZeroFloat

fun MovieEntity.movieEntityToDetailItem(): DetailItem {
    return DetailItem(
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

fun SeriesEntity.seriesEntityToDetailItem(): DetailItem {
    return DetailItem(
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

fun TopMovieEntity.topMovieEntityToDetailItem(): DetailItem {
    return DetailItem(
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

fun TopSeriesEntity.topSeriesEntityToDetailItem(): DetailItem {
    return DetailItem(
        id = id,
        title = title,
        popularity = popularity.toString(),
        posterPath = posterPath.orEmpty(),
        releaseDate = releaseDate,
        voteAverage = voteAverage.toString(),
        voteCount = voteCount.toString(),
        adult = adult,
        runTime = runtime.toString(),
        originalLanguage = originalLanguage,
        overview = if (overview == "") "No Detail" else overview
    )
}

fun UserEntity.toUserEntityToUserItem() : UserItem{
    return UserItem(
        name = name,
        surname = surname,
        signInEmail = email,
        watched = watched.orZero()
    )
}

fun UserItem.toUserItemToUserEntity() : UserEntity{
    return UserEntity(
        id = id,
        name = name,
        surname = surname,
        email = signInEmail,
        watched = watched
    )
}