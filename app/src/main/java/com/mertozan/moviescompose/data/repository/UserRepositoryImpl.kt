package com.mertozan.moviescompose.data.repository

import com.mertozan.moviescompose.data.mapper.toUserItemToUserEntity
import com.mertozan.moviescompose.data.model.entity.UserEntity
import com.mertozan.moviescompose.data.source.local.LocalDataSource
import com.mertozan.moviescompose.data.source.remote.FirebaseDataSource
import com.mertozan.moviescompose.domain.model.UserModel
import com.mertozan.moviescompose.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val firebaseDataSource: FirebaseDataSource,
) : UserRepository {

    override suspend fun addUserToLocal(user: UserEntity) {
        localDataSource.addUserToLocal(user = user)
    }

    override suspend fun getSingleLocalUser(): UserEntity {
        return localDataSource.getSingleLocalUser()
    }

    override suspend fun deleteUserFromLocale(userEntity: UserEntity) {
        return localDataSource.deleteUserFromLocal(user = userEntity)
    }

    override suspend fun getUserFromLocale(): UserModel {
        return firebaseDataSource.getUser()
    }

    override suspend fun getUserFromNetwork(): UserModel {
        return firebaseDataSource.getUserFromNetwork()
    }

    override suspend fun updateUserWatchState(userWatched: Int) {
        localDataSource.updateUserWatchState(userWatched)
    }

    override suspend fun updateUserLanguage(language: String) {
        return localDataSource.updateUserLanguage(language = language)
    }

    override suspend fun signOut() {
        firebaseDataSource.signOut()
    }

    override fun getRowCount(): Int {
        return localDataSource.getRowCount()
    }

    override suspend fun transferUserToLocal(userModel: UserModel) {
        localDataSource.addUserToLocal(userModel.toUserItemToUserEntity())
    }

}