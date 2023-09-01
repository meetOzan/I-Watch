package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.domain.repository.ContentRepository
import javax.inject.Inject

class UpdateMovieFavorite @Inject constructor(
    private val contentRepository: ContentRepository
) {
    operator fun invoke(movieId: Int,isFavorite: Boolean){
        contentRepository.updateMovieFavorite(movieId = movieId, isFavorite = isFavorite)
    }
}