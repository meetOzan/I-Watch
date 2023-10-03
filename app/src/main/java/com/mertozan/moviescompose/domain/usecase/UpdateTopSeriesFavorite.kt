package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.repository.ContentRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpdateTopSeriesFavorite @Inject constructor(
    private val contentRepository: ContentRepository
) {
    operator fun invoke(seriesId: Int,isFavorite: Boolean): NetworkResponse<Unit> {
        return try {
            NetworkResponse.Success(
                contentRepository.updateTopSeriesFavorite(seriesId = seriesId, isFavorite = isFavorite)
            )
        } catch (e: HttpException) {
            NetworkResponse.Error(e)
        } catch (e: IOException) {
            NetworkResponse.Error(e)
        }
    }
}