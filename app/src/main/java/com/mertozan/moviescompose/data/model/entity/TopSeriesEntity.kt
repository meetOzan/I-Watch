package com.mertozan.moviescompose.data.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity("top_series")
@Parcelize
data class TopSeriesEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "top_series_id") val id: Int = 0,

    @ColumnInfo(name = "top_series_title") val title: String = "",

    @ColumnInfo(name = "top_series_popularity") val popularity: Float = 0f,

    @ColumnInfo(name = "top_series_adult") val adult: Boolean,

    // @ColumnInfo(name = "movie_genres") val genres: List<Genres>,

    @ColumnInfo(name = "top_series_posterPath") val posterPath: String? = "",

    @ColumnInfo(name = "top_series_is_favorite") val isFavorite: Boolean,

    @ColumnInfo(name = "top_series_vote_average") val voteAverage: Float? = 0f,

    @ColumnInfo(name = "top_series_vote_count") val voteCount: Int? = 0,

    @ColumnInfo(name = "top_series_date") val releaseDate: String = "",

    @ColumnInfo(name = "top_series_overview") val overview: String = "",

    @ColumnInfo(name = "top_series_runtime") val runtime: Int = 0,

    @ColumnInfo(name = "top_series_language") val originalLanguage: String = ""

) : Parcelable