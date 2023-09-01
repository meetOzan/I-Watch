package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.mapper.topMovieEntityToMovieModel
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.repository.MovieRepository
import javax.inject.Inject

class GetSingleTopMovie @Inject constructor(
    private val movieRepository: MovieRepository
){
    operator fun invoke(movieId: Int) : ContentModel{
        return movieRepository.getSingleTopMovies(movieId = movieId).topMovieEntityToMovieModel()
    }
}