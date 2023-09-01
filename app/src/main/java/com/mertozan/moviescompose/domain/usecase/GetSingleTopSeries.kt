package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.mapper.topSeriesEntityToDetailItem
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.repository.MovieRepository
import javax.inject.Inject

class GetSingleTopSeries @Inject constructor(
    private val movieRepository: MovieRepository
){
    operator fun invoke(seriesId: Int) : ContentModel{
        return movieRepository.getSingleTopSeries(seriesId = seriesId).topSeriesEntityToDetailItem()
    }
}