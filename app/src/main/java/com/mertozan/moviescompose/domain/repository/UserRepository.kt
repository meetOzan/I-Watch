package com.mertozan.moviescompose.domain.repository

import com.mertozan.moviescompose.data.model.entity.UserEntity
import com.mertozan.moviescompose.domain.model.UserModel

interface UserRepository {

    suspend fun addUserToLocal(user: UserEntity)

    suspend fun getUserFromLocale(): UserModel

    suspend fun getUserFromNetwork(): UserModel

    suspend fun updateUserWatchState(userWatched: Int)

    suspend fun updateUserLanguage(language: String)

    suspend fun getSingleLocalUser(): UserEntity

    suspend fun deleteUserFromLocale(userEntity: UserEntity)

    suspend fun transferUserToLocal(userModel: UserModel)

    suspend fun signOut()

    fun getRowCount() : Int

}