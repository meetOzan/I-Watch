package com.mertozan.moviescompose.data.mapper

import com.mertozan.moviescompose.data.model.dto.User
import com.mertozan.moviescompose.data.model.entity.UserEntity
import com.mertozan.moviescompose.domain.model.UserModel
import com.mertozan.moviescompose.util.extensions.orZero

fun UserEntity.toUserEntityToUserItem(): UserModel {
    return UserModel(
        name = name,
        surname = surname,
        signInEmail = email,
        watched = watched.orZero()
    )
}

fun UserModel.toUserItemToUserEntity(): UserEntity {
    return UserEntity(
        id = 1,
        name = name,
        surname = surname,
        email = signInEmail,
        watched = watched
    )
}

fun User.userToUserModel() : UserModel{
    return UserModel(
        id = 1,
        name = name,
        surname = surname,
        signInEmail = email,
        watched = watched
    )
}