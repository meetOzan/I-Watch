package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.domain.repository.MovieRepository
import javax.inject.Inject

class UpdateTopMovieFavorite @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(movieId: Int,isFavorite: Boolean){
        movieRepository.updateTopMovieFavorite(movieId = movieId, isFavorite = isFavorite)
    }
}