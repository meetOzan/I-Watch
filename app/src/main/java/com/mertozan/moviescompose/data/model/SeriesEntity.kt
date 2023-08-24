package com.mertozan.moviescompose.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("series_entity")
data class SeriesEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "fav_series_id") val id: Int = 0,

    @ColumnInfo(name = "fav_series_title") val title: String = "",

    @ColumnInfo(name = "fav_series_popularity") val popularity: Float? = 0f,

    @ColumnInfo(name = "fav_series_adult") val adult: Boolean,

    // @ColumnInfo(name = "fav_series_genres") val genres: List<Genres>,

    @ColumnInfo(name = "fav_series_posterPath") val posterPath: String? = "",

    @ColumnInfo(name = "fav_series_is_favorite") val isFavorite : Boolean = true,

    @ColumnInfo(name = "fav_series_airDate") val firstAirDate: String = "",

    @ColumnInfo(name = "fav_series_note_overview") val overview: String = "",

    @ColumnInfo(name = "fav_series_episode") val episodeNumber: Int? = 0,

    @ColumnInfo(name = "fav_series_originalLanguage") val originalLanguage: String = ""

) : Parcelable