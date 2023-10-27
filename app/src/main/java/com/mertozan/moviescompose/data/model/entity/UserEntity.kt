package com.mertozan.moviescompose.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user_entity")
data class UserEntity(

    @PrimaryKey
    @ColumnInfo("user_id")
    val id: Int?,

    @ColumnInfo("user_name")
    val name: String?,

    @ColumnInfo("user_surname")
    val surname: String?,

    @ColumnInfo("user_watched")
    val watched: Int?,

    @ColumnInfo("user_language")
    val language: String?

)
