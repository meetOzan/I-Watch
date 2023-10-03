package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.model.dto.GenresResponse
import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.repository.ContentRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSeriesGenres @Inject constructor(
    private val contentRepository: ContentRepository
){
    suspend operator fun invoke() : NetworkResponse<GenresResponse> {
        return try {
            NetworkResponse.Success(
                contentRepository.getSeriesGenres()
            )
        } catch (e: HttpException) {
            NetworkResponse.Error(e)
        } catch (e: IOException) {
            NetworkResponse.Error(e)
        }
    }
}