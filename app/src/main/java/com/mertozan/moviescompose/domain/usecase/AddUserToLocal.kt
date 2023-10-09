package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.model.entity.UserEntity
import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.repository.UserRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddUserToLocal @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userEntity: UserEntity): NetworkResponse<Unit> {
        return try {
            NetworkResponse.Success(
                userRepository.addUserToLocal(userEntity)
            )
        } catch (e: HttpException) {
            NetworkResponse.Error(e)
        } catch (e: IOException) {
            NetworkResponse.Error(e)
        }
    }
}