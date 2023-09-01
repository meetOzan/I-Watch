package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.domain.repository.MovieRepository
import javax.inject.Inject

class UpdateMovieFavorite @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(movieId: Int,isFavorite: Boolean){
        movieRepository.updateMovieFavorite(movieId = movieId, isFavorite = isFavorite)
    }
}