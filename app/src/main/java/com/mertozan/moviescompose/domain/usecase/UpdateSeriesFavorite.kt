package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.domain.repository.ContentRepository
import javax.inject.Inject

class UpdateSeriesFavorite @Inject constructor(
    private val contentRepository: ContentRepository
) {
    operator fun invoke(seriesId: Int,isFavorite: Boolean){
        contentRepository.updateSeriesFavorite(seriesId = seriesId, isFavorite = isFavorite)
    }
}