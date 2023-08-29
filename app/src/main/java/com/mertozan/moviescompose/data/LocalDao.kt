package com.mertozan.moviescompose.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.mertozan.moviescompose.data.model.entity.MovieEntity
import com.mertozan.moviescompose.data.model.entity.SeriesEntity
import com.mertozan.moviescompose.data.model.entity.TopMovieEntity
import com.mertozan.moviescompose.data.model.entity.TopSeriesEntity

@Dao
interface LocalDao {

    @Upsert
    fun addMovieToLocal(movieList: List<MovieEntity>)

    @Upsert
    fun addSeriesToLocal(seriesList: List<SeriesEntity>)

    @Upsert
    fun addTopMoviesToLocal(topMovies: List<TopMovieEntity>)

    @Upsert
    fun addTopSeriesToLocal(topSeries: List<TopSeriesEntity>)

    @Query("SELECT * FROM movies_entity")
    fun getPopularMovies(): List<MovieEntity>

    @Query("SELECT * FROM series_entity")
    fun getPopularSeries(): List<SeriesEntity>

    @Query("SELECT * FROM top_movies")
    fun getTopMovies(): List<TopMovieEntity>

    @Query("SELECT * FROM top_series")
    fun getTopSeries(): List<TopSeriesEntity>

    @Query("SELECT * FROM movies_entity WHERE movie_id = :movieId")
    fun getSingleLocalMovie(movieId: Int): MovieEntity

    @Query("SELECT * FROM series_entity WHERE series_id = :seriesId")
    fun getSingleLocalSeries(seriesId: Int): SeriesEntity

    @Query("SELECT * FROM top_movies WHERE top_movie_id = :movieId")
    fun getTopSingleLocalMovie(movieId: Int): TopMovieEntity

    @Query("SELECT * FROM top_series WHERE top_series_id = :seriesId")
    fun getTopSingleLocalSeries(seriesId: Int): TopSeriesEntity

    @Query("UPDATE movies_entity SET movie_is_favorite = :isFavorite WHERE movie_id = :movieId")
    fun updateMovieFavoriteState(movieId: Int, isFavorite: Boolean)

    @Query("UPDATE series_entity SET series_is_favorite = :isFavorite WHERE series_id = :seriesId")
    fun updateSeriesFavoriteState(seriesId: Int, isFavorite: Boolean)

    @Query("UPDATE top_movies SET top_movie_is_favorite = :isFavorite WHERE top_movie_id = :topMovieId")
    fun updateTopMovieFavoriteState(topMovieId: Int, isFavorite: Boolean)

    @Query("UPDATE top_series SET top_series_is_favorite = :isFavorite WHERE top_series_id = :topSeriesId")
    fun updateTopSeriesFavoriteState(topSeriesId: Int, isFavorite: Boolean)

    @Delete
    fun deleteMovie(movie: MovieEntity)

    @Delete
    fun deleteSeries(series: SeriesEntity)

}