package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.domain.repository.MovieRepository
import javax.inject.Inject

class TransferUserToLocal @Inject constructor(
    private val movieRepository: MovieRepository,
    private val getUserFromNetwork: GetUserFromNetwork
    ) {
    suspend operator fun invoke() {
        movieRepository.transferUserToLocal(getUserFromNetwork.invoke())
    }
}