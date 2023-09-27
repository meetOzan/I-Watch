package com.mertozan.moviescompose.data.mapper

import com.mertozan.moviescompose.data.model.dto.User
import com.mertozan.moviescompose.data.model.entity.UserEntity
import com.mertozan.moviescompose.domain.model.UserModel

fun UserEntity.toUserEntityToUserItem(): UserModel {
    return UserModel(
        name = name,
        surname = surname,
        signInEmail = email,
        watched = watched
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