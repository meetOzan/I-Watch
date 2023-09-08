package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.mapper.moviesToMoviesModelList
import com.mertozan.moviescompose.data.mapper.toSeriesDetailItemList
import com.mertozan.moviescompose.data.mapper.toTopSeriesDetailItemList
import com.mertozan.moviescompose.data.mapper.topMoviesToMovieModelList
import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.repository.ContentRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllFavorites @Inject constructor(
    private val contentRepository: ContentRepository
) {
    operator fun invoke(): NetworkResponse<List<ContentModel>> {
        return try {
            NetworkResponse.Success(
                contentRepository.getFavoritePopularSeries().toSeriesDetailItemList()
                    .plus(contentRepository.getFavoriteTopMovies().topMoviesToMovieModelList())
                    .plus(contentRepository.getFavoriteTopSeries().toTopSeriesDetailItemList())
                    .plus(contentRepository.getFavoritePopularMovies().moviesToMoviesModelList())
            )
        } catch (e: HttpException) {
            NetworkResponse.Error(e)
        } catch (e: IOException) {
            NetworkResponse.Error(e)
        }
    }
}