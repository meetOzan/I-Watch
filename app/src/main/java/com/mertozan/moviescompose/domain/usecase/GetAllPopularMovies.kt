package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.mapper.moviesToMoviesModelList
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.repository.MovieRepository
import javax.inject.Inject

class GetAllPopularMovies @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): List<ContentModel> {
        return movieRepository.getAllPopularMovies().moviesToMoviesModelList()
    }
}