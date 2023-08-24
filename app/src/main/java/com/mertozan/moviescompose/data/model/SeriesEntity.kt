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
    @ColumnInfo(name = "series_id") val id: Int = 0,

    @ColumnInfo(name = "series_title") val title: String = "",

    @ColumnInfo(name = "series_popularity") val popularity: Float? = 0f,

    @ColumnInfo(name = "series_adult") val adult: Boolean,

    // @ColumnInfo(name = "eries_genres") val genres: List<Genres>,

    @ColumnInfo(name = "series_posterPath") val posterPath: String? = "",

    @ColumnInfo(name = "series_is_favorite") val isFavorite : Boolean,

    @ColumnInfo(name = "series_airDate") val firstAirDate: String = "",

    @ColumnInfo(name = "series_note_overview") val overview: String = "",

    @ColumnInfo(name = "series_episode") val episodeNumber: Int? = 0,

    @ColumnInfo(name = "series_originalLanguage") val originalLanguage: String = ""

) : Parcelable