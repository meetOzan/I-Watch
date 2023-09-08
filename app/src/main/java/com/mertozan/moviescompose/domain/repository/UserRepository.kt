package com.mertozan.moviescompose.domain.repository

import com.mertozan.moviescompose.data.model.entity.UserEntity
import com.mertozan.moviescompose.domain.model.UserModel

interface UserRepository {

    fun addUserToLocal(user: UserEntity)

    suspend fun getUserFromLocale(): UserModel

    suspend fun getUserFromNetwork(): UserModel

    fun getSingleLocalUser(): UserModel

    suspend fun transferUserToLocal(userModel: UserModel)

    suspend fun signOut()

}