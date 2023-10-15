package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.mapper.toUserEntityToUserItem
import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.model.UserModel
import com.mertozan.moviescompose.domain.repository.UserRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSingleLocalUser @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): NetworkResponse<UserModel> {
        return try {
            NetworkResponse.Success(
                userRepository.getSingleLocalUser().toUserEntityToUserItem()
            )
        } catch (e: HttpException) {
            NetworkResponse.Error(e)
        } catch (e: IOException) {
            NetworkResponse.Error(e)
        }
    }
}