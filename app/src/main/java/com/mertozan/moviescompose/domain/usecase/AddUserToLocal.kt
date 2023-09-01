package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.model.entity.UserEntity
import com.mertozan.moviescompose.domain.repository.UserRepository
import javax.inject.Inject

class AddUserToLocal @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(userEntity: UserEntity) {
        userRepository.addUserToLocal(userEntity)
    }
}