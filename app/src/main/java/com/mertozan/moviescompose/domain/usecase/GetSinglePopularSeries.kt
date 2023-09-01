package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.mapper.seriesEntityToDetailItem
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.repository.ContentRepository
import javax.inject.Inject

class GetSinglePopularSeries @Inject constructor(
    private val contentRepository: ContentRepository
){
    operator fun invoke(seriesId: Int): ContentModel{
        return contentRepository.getSingleSeries(seriesId = seriesId).seriesEntityToDetailItem()
    }
}