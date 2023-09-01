package com.mertozan.moviescompose.data.repository

import com.mertozan.moviescompose.data.local.datasource.LocalDataSource
import com.mertozan.moviescompose.data.mapper.toUserItemToUserEntity
import com.mertozan.moviescompose.data.model.entity.UserEntity
import com.mertozan.moviescompose.data.remote.firebase.FirebaseDataSource
import com.mertozan.moviescompose.domain.model.UserModel
import com.mertozan.moviescompose.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val firebaseDataSource: FirebaseDataSource,
):UserRepository {

    override fun addUserToLocal(user: UserEntity) {
        localDataSource.addUserToLocal(user = user)
    }

    override fun getSingleLocalUser(): UserModel {
        return localDataSource.getSingleLocalUser()
    }

    override suspend fun getUserFromLocale(): UserModel {
        return firebaseDataSource.getUser()
    }

    override suspend fun getUserFromNetwork(): UserModel {
        return firebaseDataSource.getUserFromNetwork()
    }

    override suspend fun signOut() {
        firebaseDataSource.signOut()
    }

    override suspend fun transferUserToLocal(userModel: UserModel) {
        localDataSource.addUserToLocal(userModel.toUserItemToUserEntity())
    }

}