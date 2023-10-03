package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.repository.UserRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TransferUserToLocal @Inject constructor(
    private val userRepository: UserRepository,
    private val getUserFromNetwork: GetUserFromNetwork
) {
    suspend operator fun invoke(): NetworkResponse<Unit> {
        return try {
            NetworkResponse.Success(
                userRepository.transferUserToLocal(getUserFromNetwork.invoke())
            )
        } catch (e: HttpException) {
            NetworkResponse.Error(e)
        } catch (e: IOException) {
            NetworkResponse.Error(e)
        }
    }
}
