package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.domain.model.UserModel
import com.mertozan.moviescompose.domain.repository.UserRepository
import javax.inject.Inject

class GetUserFromLocal @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): UserModel {
        return userRepository.getUserFromLocale()
    }
}