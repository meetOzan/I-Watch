package com.mertozan.moviescompose.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("movies_entity")
data class MovieEntity(

    @PrimaryKey
    @ColumnInfo(name = "movie_id") val id: Int = 0,

    @ColumnInfo(name = "movie_title") val title: String = "",

    @ColumnInfo(name = "movie_popularity") val popularity: Float = 0f,

    @ColumnInfo(name = "movie_adult") val adult: Boolean,

    // @ColumnInfo(name = "movie_genres") val genres: List<Genres>,

    @ColumnInfo(name = "movie_posterPath") val posterPath: String = "",

    @ColumnInfo(name = "movie_vote_average") val voteAverage: Float = 0f,

    @ColumnInfo(name = "movie_vote_count") val voteCount: Int? = 0,

    @ColumnInfo(name = "movie_is_favorite") val isFavorite: Boolean,

    @ColumnInfo(name = "movie_date") val releaseDate: String = "",

    @ColumnInfo(name = "movie_overview") val overview: String = "",

    @ColumnInfo(name = "movie_runtime") val runtime: Int = 0,

    @ColumnInfo(name = "movie_language") val originalLanguage: String = ""

)
