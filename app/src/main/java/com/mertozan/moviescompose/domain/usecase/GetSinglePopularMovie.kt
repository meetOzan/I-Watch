package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.mapper.movieEntityToContentModel
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.repository.ContentRepository
import javax.inject.Inject

class GetSinglePopularMovie @Inject constructor(
    private val contentRepository: ContentRepository
){
    operator fun invoke(movieId: Int) : ContentModel{
        return contentRepository.getSingleMovie(movieId = movieId).movieEntityToContentModel()
    }
}