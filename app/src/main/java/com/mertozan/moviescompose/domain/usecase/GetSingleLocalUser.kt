package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.domain.repository.MovieRepository
import javax.inject.Inject

class GetSingleLocalUser @Inject constructor(
    private val movieRepository: MovieRepository
){
    operator fun invoke(){
        movieRepository.getSingleLocalUser()
    }
}