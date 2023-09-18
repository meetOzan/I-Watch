package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.mapper.moviesToMoviesModelList
import com.mertozan.moviescompose.data.mapper.toSeriesMovieModelList
import com.mertozan.moviescompose.data.mapper.toTopSeriesDetailItemList
import com.mertozan.moviescompose.data.mapper.topMoviesToMovieModelList
import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.repository.ContentRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllWatchedListContents @Inject constructor(
    private val contentRepository: ContentRepository
) {
    operator fun invoke(): NetworkResponse<List<ContentModel>> {
        return try {
            NetworkResponse.Success(
                contentRepository.getAllWatchedPopularMovies().moviesToMoviesModelList()
                    .plus(contentRepository.getAllWatchedTopMovies().topMoviesToMovieModelList())
                    .plus(contentRepository.getAllWatchedPopularSeries().toSeriesMovieModelList())
                    .plus(contentRepository.getAllWatchedTopSeries().toTopSeriesDetailItemList())
            )
        } catch (e: HttpException) {
            NetworkResponse.Error(e)
        } catch (e: IOException) {
            NetworkResponse.Error(e)
        }
    }
}