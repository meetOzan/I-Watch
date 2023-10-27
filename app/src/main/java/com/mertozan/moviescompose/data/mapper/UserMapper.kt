package com.mertozan.moviescompose.data.mapper

import com.mertozan.moviescompose.data.model.entity.UserEntity
import com.mertozan.moviescompose.domain.model.UserModel
import com.mertozan.moviescompose.util.extensions.orZero

fun UserEntity.toUserEntityToUserItem(): UserModel {
    return UserModel(
        name = name.orEmpty(),
        surname = surname.orEmpty(),
        watched = watched.orZero(),
        language = language.orEmpty()
    )
}

fun UserModel.toUserItemToUserEntity(): UserEntity {
    return UserEntity(
        id = 1,
        name = name,
        surname = surname,
        watched = watched,
        language = language
    )
}