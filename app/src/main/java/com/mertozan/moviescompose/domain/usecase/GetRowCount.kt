package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.repository.UserRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRowCount @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): NetworkResponse<Int> {
        return try {
            NetworkResponse.Success(
                userRepository.getRowCount()
            )
        } catch (e: HttpException) {
            NetworkResponse.Error(e)
        } catch (e: IOException) {
            NetworkResponse.Error(e)
        }
    }
}