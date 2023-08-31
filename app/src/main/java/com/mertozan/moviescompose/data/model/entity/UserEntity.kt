package com.mertozan.moviescompose.data.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity("user_entity")
@Parcelize
data class UserEntity(

    @PrimaryKey
    @ColumnInfo("user_id")
    val id : Int,

    @ColumnInfo("user_name")
    val name: String,

    @ColumnInfo("user_surname")
    val surname: String,

    @ColumnInfo("user_email")
    val email: String,

    @ColumnInfo("user_watched")
    val watched: Int
    
) : Parcelable
