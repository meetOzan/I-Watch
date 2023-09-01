package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.domain.model.UserModel
import com.mertozan.moviescompose.domain.repository.MovieRepository
import javax.inject.Inject

class GetUserFromLocal @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): UserModel {
        return movieRepository.getUserFromLocale()
    }
}