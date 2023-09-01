package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.domain.repository.ContentRepository
import javax.inject.Inject

class GetSeriesGenres @Inject constructor(
    private val contentRepository: ContentRepository
){
    suspend operator fun invoke(){
        contentRepository.getSeriesGenres()
    }
}