package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.domain.repository.ContentRepository
import javax.inject.Inject

class GetMoviesGenres @Inject constructor(
    private val contentRepository: ContentRepository
){
    suspend operator fun invoke(){
        contentRepository.getMovieGenres()
    }
}