package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.mapper.movieEntityToContentModel
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.repository.MovieRepository
import javax.inject.Inject

class GetSinglePopularMovie @Inject constructor(
    private val movieRepository: MovieRepository
){
    operator fun invoke(movieId: Int) : ContentModel{
        return movieRepository.getSingleMovie(movieId = movieId).movieEntityToContentModel()
    }
}