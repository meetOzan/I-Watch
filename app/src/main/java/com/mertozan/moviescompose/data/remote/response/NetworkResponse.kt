package com.mertozan.moviescompose.data.remote.response

sealed class NetworkResponse<out T : Any> {
    data class Success<out T: Any>(val data: T) : NetworkResponse<T>()
    data class Error(val error: Exception) : NetworkResponse<Nothing>()
}
