package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.mapper.topMoviesToMovieModelList
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.repository.ContentRepository
import javax.inject.Inject

class GetAllTopRatedMovies @Inject constructor(
    private val contentRepository: ContentRepository
){
    operator fun invoke() : List<ContentModel>{
        return contentRepository.getAllTopRatedMovies().topMoviesToMovieModelList()
    }
}