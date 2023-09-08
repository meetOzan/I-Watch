package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.mapper.movieEntityToContentModel
import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.repository.ContentRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSinglePopularMovie @Inject constructor(
    private val contentRepository: ContentRepository
){
    operator fun invoke(movieId: Int) : NetworkResponse<ContentModel>{
        return try {
            NetworkResponse.Success(
                contentRepository.getSingleMovie(movieId = movieId)
                    .movieEntityToContentModel()
            )
        }catch (e: HttpException) {
            NetworkResponse.Error(e)
        } catch (e: IOException) {
            NetworkResponse.Error(e)
        }
    }
}