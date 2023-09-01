package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.mapper.seriesEntityToDetailItem
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.repository.MovieRepository
import javax.inject.Inject

class GetSinglePopularSeries @Inject constructor(
    private val movieRepository: MovieRepository
){
    operator fun invoke(seriesId: Int): ContentModel{
        return movieRepository.getSingleSeries(seriesId = seriesId).seriesEntityToDetailItem()
    }
}