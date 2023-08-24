package com.mertozan.moviescompose.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("movies_entity")
data class MovieEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movie_id") val id: Int = 0,

    @ColumnInfo(name = "movie_title") val title: String = "",

    @ColumnInfo(name = "movie_popularity") val popularity: Float = 0f,

    @ColumnInfo(name = "movie_adult") val adult: Boolean,

    // @ColumnInfo(name = "movie_genres") val genres: List<Genres>,

    @ColumnInfo(name = "movie_posterPath") val posterPath: String? = "",

    @ColumnInfo(name = "movie_is_favorite") val isFavorite: Boolean,

    @ColumnInfo(name = "movie_date") val releaseDate: String = "",

    @ColumnInfo(name = "movie_overview") val overview: String = "",

    @ColumnInfo(name = "movie_runtime") val runtime: Int = 0,

    @ColumnInfo(name = "fav_movie_Language") val originalLanguage: String = ""

) : Parcelable
