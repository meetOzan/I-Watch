package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.mapper.toSeriesDetailItemList
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.repository.ContentRepository
import javax.inject.Inject

class GetAllPopularSeries @Inject constructor(
    private val contentRepository: ContentRepository
) {
    operator fun invoke(): List<ContentModel> {
        return contentRepository.getAllPopularSeries().toSeriesDetailItemList()
    }
}