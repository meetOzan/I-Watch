package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.domain.repository.UserRepository
import javax.inject.Inject

class TransferUserToLocal @Inject constructor(
    private val userRepository: UserRepository,
    private val getUserFromNetwork: GetUserFromNetwork
) {
    suspend operator fun invoke() {
        userRepository.transferUserToLocal(getUserFromNetwork.invoke())
    }
}