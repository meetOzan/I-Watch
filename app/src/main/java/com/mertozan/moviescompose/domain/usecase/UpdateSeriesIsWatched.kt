package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.repository.ContentRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpdateSeriesIsWatched @Inject constructor(
    private val contentRepository: ContentRepository
) {
    operator fun invoke(seriesId: Int, isWatched: Boolean): NetworkResponse<Unit> {
        return try {
            NetworkResponse.Success(
                contentRepository.updatePopularSeriesIsWatched(
                    seriesId = seriesId,
                    isWatched = isWatched
                )
            )
        } catch (e: HttpException) {
            NetworkResponse.Error(e)
        } catch (e: IOException) {
            NetworkResponse.Error(e)
        }
    }
}