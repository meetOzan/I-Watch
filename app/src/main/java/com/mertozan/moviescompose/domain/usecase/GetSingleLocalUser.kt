package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.domain.repository.ContentRepository
import com.mertozan.moviescompose.domain.repository.UserRepository
import javax.inject.Inject

class GetSingleLocalUser @Inject constructor(
    private val userRepository: UserRepository
){
    operator fun invoke(){
        userRepository.getSingleLocalUser()
    }
}