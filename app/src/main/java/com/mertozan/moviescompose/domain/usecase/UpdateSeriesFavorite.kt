package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.domain.repository.MovieRepository
import javax.inject.Inject

class UpdateSeriesFavorite @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(seriesId: Int,isFavorite: Boolean){
        movieRepository.updateSeriesFavorite(seriesId = seriesId, isFavorite = isFavorite)
    }
}