package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.repository.MovieRepository
import javax.inject.Inject

class GetAllTopRatedMovies @Inject constructor(
    private val movieRepository: MovieRepository
){
    operator fun invoke() : List<ContentModel>{
        return movieRepository.getAllTopRatedMovies()
    }
}