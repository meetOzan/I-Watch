package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.model.entity.UserEntity
import com.mertozan.moviescompose.domain.repository.MovieRepository
import javax.inject.Inject

class AddUserToLocal @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(userEntity: UserEntity){
        movieRepository.addUserToLocal(userEntity)
    }
}