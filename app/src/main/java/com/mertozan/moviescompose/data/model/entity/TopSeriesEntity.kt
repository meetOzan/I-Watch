package com.mertozan.moviescompose.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mertozan.moviescompose.util.enums.ContentType
import com.mertozan.moviescompose.util.enums.ListType

@Entity("top_series")
data class TopSeriesEntity(

    @PrimaryKey
    @ColumnInfo(name = "top_series_id") val id: Int = 0,

    @ColumnInfo(name = "top_series_title") val title: String = "",

    @ColumnInfo(name = "top_series_popularity") val popularity: Float = 0f,

    @ColumnInfo(name = "top_series_adult") val adult: Boolean,

    // @ColumnInfo(name = "movie_genres") val genres: List<Genres>,

    @ColumnInfo(name = "top_series_posterPath") val posterPath: String? = "",

    @ColumnInfo(name = "top_series_is_favorite") val isFavorite: Boolean,

    @ColumnInfo(name = "top_series_is_watched") val isWatched: Boolean,

    @ColumnInfo(name = "top_series_is_in_watch_list") val isInWatchList: Boolean,

    @ColumnInfo(name = "top_series_type") val type: String = ContentType.SERIES.name,

    @ColumnInfo(name = "top_series_list_type") val listType: String = ListType.TOP_RATED.name,

    @ColumnInfo(name = "top_series_vote_average") val voteAverage: Float? = 0f,

    @ColumnInfo(name = "top_series_vote_count") val voteCount: Int? = 0,

    @ColumnInfo(name = "top_series_date") val firstAirDate: String = "",

    @ColumnInfo(name = "top_series_overview") val overview: String = "",

    @ColumnInfo(name = "top_series_runtime") val episodes: Int = 0,

    @ColumnInfo(name = "top_series_language") val originalLanguage: String = ""

)