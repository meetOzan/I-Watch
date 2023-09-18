package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.repository.ContentRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpdateMovieIsInWatch @Inject constructor(
    private val contentRepository: ContentRepository
) {
    operator fun invoke(movieId: Int, isInWatched: Boolean): NetworkResponse<Unit> {
        return try {
            NetworkResponse.Success(
                contentRepository.updatePopularMoviesIsInWatchList(
                    movieId = movieId,
                    isInWatched = isInWatched
                )
            )
        } catch (e: HttpException) {
            NetworkResponse.Error(e)
        } catch (e: IOException) {
            NetworkResponse.Error(e)
        }
    }
}