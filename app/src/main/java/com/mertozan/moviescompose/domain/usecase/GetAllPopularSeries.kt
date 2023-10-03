package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.mapper.toSeriesMovieModelList
import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.repository.ContentRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllPopularSeries @Inject constructor(
    private val contentRepository: ContentRepository
) {
    operator fun invoke(): NetworkResponse<List<ContentModel>> {
        return try {
            NetworkResponse.Success(
                contentRepository.getAllPopularSeries()
                    .toSeriesMovieModelList()
            )
        } catch (e: HttpException) {
            NetworkResponse.Error(e)
        } catch (e: IOException) {
            NetworkResponse.Error(e)
        }
    }
}