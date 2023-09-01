package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.mapper.topSeriesEntityToDetailItem
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.repository.ContentRepository
import javax.inject.Inject

class GetSingleTopSeries @Inject constructor(
    private val contentRepository: ContentRepository
){
    operator fun invoke(seriesId: Int) : ContentModel{
        return contentRepository.getSingleTopSeries(seriesId = seriesId).topSeriesEntityToDetailItem()
    }
}