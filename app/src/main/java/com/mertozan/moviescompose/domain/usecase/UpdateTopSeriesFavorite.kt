package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.domain.repository.ContentRepository
import javax.inject.Inject

class UpdateTopSeriesFavorite @Inject constructor(
    private val contentRepository: ContentRepository
) {
    operator fun invoke(seriesId: Int,isFavorite: Boolean){
        contentRepository.updateTopSeriesFavorite(seriesId = seriesId, isFavorite = isFavorite)
    }
}