package com.mertozan.moviescompose.domain.repository

import com.mertozan.moviescompose.data.model.entity.UserEntity
import com.mertozan.moviescompose.domain.model.UserModel

interface UserRepository {

    suspend fun addUserToLocal(user: UserEntity)

    suspend fun updateUserWatchState(userWatched: Int)

    suspend fun updateUserLanguage(language: String)

    suspend fun getSingleLocalUser(): UserEntity

    suspend fun deleteUserFromLocale(userEntity: UserEntity)

    suspend fun transferUserToLocal(userModel: UserModel)

    fun getRowCount() : Int

}