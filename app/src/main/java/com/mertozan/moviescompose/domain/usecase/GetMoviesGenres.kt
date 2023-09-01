package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesGenres @Inject constructor(
    private val movieRepository: MovieRepository
){
    suspend operator fun invoke(){
        movieRepository.getMovieGenres()
    }
}