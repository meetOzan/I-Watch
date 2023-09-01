package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.domain.repository.UserRepository
import javax.inject.Inject

class SignOut @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() {
        userRepository.signOut()
    }
}