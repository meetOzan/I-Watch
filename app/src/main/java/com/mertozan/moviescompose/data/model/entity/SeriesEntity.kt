package com.mertozan.moviescompose.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mertozan.moviescompose.util.enums.ContentType
import com.mertozan.moviescompose.util.enums.ListType

@Entity("series_entity")
data class SeriesEntity(

    @PrimaryKey
    @ColumnInfo(name = "series_id") val id: Int = 0,

    @ColumnInfo(name = "series_title") val title: String = "",

    @ColumnInfo(name = "series_popularity") val popularity: Float = 0f,

    @ColumnInfo(name = "series_adult") val adult: Boolean,

    // @ColumnInfo(name = "series_genres") val genres: List<Genres>,

    @ColumnInfo(name = "series_posterPath") val posterPath: String? = "",

    @ColumnInfo(name = "series_is_favorite") val isFavorite: Boolean,

    @ColumnInfo(name = "series_is_watched") val isWatched: Boolean,

    @ColumnInfo(name = "series_is_in_watch_list") val isInWatchList: Boolean,

    @ColumnInfo(name = "series_type") val type: String = ContentType.SERIES.name,

    @ColumnInfo(name = "series_list_type") val listType: String = ListType.POPULAR.name,

    @ColumnInfo(name = "series_date") val firstAirDate: String? = "",

    @ColumnInfo(name = "series_vote_average") val voteAverage: Float? = 0f,

    @ColumnInfo(name = "series_vote_count") val voteCount: Int? = 0,

    @ColumnInfo(name = "series_overview") val overview: String = "",

    @ColumnInfo(name = "series_runtime") val episodeNumber: Int = 0,

    @ColumnInfo(name = "series_language") val originalLanguage: String = ""

)