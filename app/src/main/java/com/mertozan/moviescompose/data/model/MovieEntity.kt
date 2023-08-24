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
    @ColumnInfo(name = "fav_movie_id") val id: Int = 0,

    @ColumnInfo(name = "fav_movie_title") val title: String = "",

    @ColumnInfo(name = "fav_movie_popularity") val popularity: Float = 0f,

    @ColumnInfo(name = "fav_movie_adult") val adult: Boolean,

    // @ColumnInfo(name = "fav_movie_genres") val genres: List<Genres>,

    @ColumnInfo(name = "fav_movie_posterPath") val posterPath: String? = "",

    @ColumnInfo(name = "fav_movie_is_favorite") val isFavorite : Boolean = true,

    @ColumnInfo(name = "fav_movie_date") val releaseDate: String = "",

    @ColumnInfo(name = "fav_movie_overview") val overview: String = "",

    @ColumnInfo(name = "fav_movie_runtime") val runtime: Int = 0,

    @ColumnInfo(name = "fav_movie_Language") val originalLanguage: String = ""

) : Parcelable
