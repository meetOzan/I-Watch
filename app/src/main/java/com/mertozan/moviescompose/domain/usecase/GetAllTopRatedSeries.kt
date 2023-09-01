package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.mapper.toTopSeriesDetailItemList
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.repository.ContentRepository
import javax.inject.Inject

class GetAllTopRatedSeries @Inject constructor(
    private val contentRepository: ContentRepository
){
    operator fun invoke() : List<ContentModel>{
        return contentRepository.getAllTopRatedSeries().toTopSeriesDetailItemList()
    }
}