package com.mertozan.moviescompose.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.mertozan.moviescompose.data.model.entity.SeriesEntity
import com.mertozan.moviescompose.data.model.entity.TopSeriesEntity

@Dao
interface SeriesDao {

    @Upsert
    fun addSeriesToLocal(seriesList: List<SeriesEntity>)

    @Upsert
    fun addTopSeriesToLocal(topSeries: List<TopSeriesEntity>)

    @Query("SELECT * FROM series_entity")
    fun getPopularSeries(): List<SeriesEntity>

    @Query("SELECT * FROM top_series")
    fun getTopSeries(): List<TopSeriesEntity>

    @Query("SELECT * FROM series_entity WHERE series_id = :seriesId")
    fun getSingleLocalSeries(seriesId: Int): SeriesEntity

    @Query("SELECT * FROM top_series WHERE top_series_id = :seriesId")
    fun getTopSingleLocalSeries(seriesId: Int): TopSeriesEntity

    @Query("UPDATE series_entity SET series_is_favorite = :isFavorite WHERE series_id = :seriesId")
    fun updateSeriesFavoriteState(seriesId: Int, isFavorite: Boolean)

    @Query("UPDATE top_series SET top_series_is_favorite = :isFavorite WHERE top_series_id = :topSeriesId")
    fun updateTopSeriesFavoriteState(topSeriesId: Int, isFavorite: Boolean)

    @Delete
    fun deleteSeries(series: SeriesEntity)

}