package com.mertozan.moviescompose.data.local.dao

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
    fun getSinglePopularSeries(seriesId: Int): SeriesEntity

    @Query("SELECT * FROM top_series WHERE top_series_id = :seriesId")
    fun getTopSingleLocalSeries(seriesId: Int): TopSeriesEntity

    @Query("SELECT * FROM series_entity WHERE series_is_favorite = 1")
    fun getFavoritePopularSeries(): List<SeriesEntity>

    @Query("SELECT * FROM series_entity WHERE series_is_in_watch_list = 1")
    fun getInWatchListPopularSeries(): List<SeriesEntity>

    @Query("SELECT * FROM series_entity WHERE series_is_watched = 1")
    fun getWatchedPopularSeries(): List<SeriesEntity>

    @Query("SELECT * FROM top_series WHERE top_series_is_favorite = 1")
    fun getFavoriteTopLocalSeries(): List<TopSeriesEntity>

    @Query("SELECT * FROM top_series WHERE top_series_is_in_watch_list = 1")
    fun getInWatchListTopSeries(): List<TopSeriesEntity>

    @Query("SELECT * FROM top_series WHERE top_series_is_watched = 1")
    fun getWatchedTopSeries(): List<TopSeriesEntity>

    @Query("UPDATE series_entity SET series_is_favorite = :isFavorite WHERE series_id = :seriesId")
    fun updateSeriesFavoriteState(seriesId: Int, isFavorite: Boolean)

    @Query("UPDATE series_entity SET series_is_watched = :isWatched WHERE series_id = :seriesId")
    fun updateSeriesIsWatchedState(seriesId: Int, isWatched: Boolean)

    @Query("UPDATE series_entity SET series_is_in_watch_list = :isInWatch WHERE series_id = :seriesId")
    fun updateSeriesInWatchListState(seriesId: Int, isInWatch: Boolean)

    @Query("UPDATE top_series SET top_series_is_favorite = :isFavorite WHERE top_series_id = :topSeriesId")
    fun updateTopSeriesFavoriteState(topSeriesId: Int, isFavorite: Boolean)

    @Query("UPDATE top_series SET top_series_is_watched = :isWatched WHERE top_series_id = :seriesId")
    fun updateTopSeriesIsWatchedState(seriesId: Int, isWatched: Boolean)

    @Query("UPDATE top_series SET top_series_is_in_watch_list = :isInWatch WHERE top_series_id = :seriesId")
    fun updateTopSeriesInWatchListState(seriesId: Int, isInWatch: Boolean)

    @Delete
    fun deleteSeries(series: SeriesEntity)

}