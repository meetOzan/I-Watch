package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.domain.repository.MovieRepository
import javax.inject.Inject

class UpdateTopSeriesFavorite @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(seriesId: Int,isFavorite: Boolean){
        movieRepository.updateTopSeriesFavorite(seriesId = seriesId, isFavorite = isFavorite)
    }
}