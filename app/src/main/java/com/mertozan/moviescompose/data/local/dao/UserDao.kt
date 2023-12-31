package com.mertozan.moviescompose.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.mertozan.moviescompose.data.model.entity.UserEntity

@Dao
interface UserDao {
    @Upsert
    fun addUserToLocal(user: UserEntity)

    @Query("SELECT COUNT(*) FROM user_entity")
    fun getRowCount(): Int

    @Delete
    fun deleteUserFromLocal(user: UserEntity)

    @Query("SELECT * FROM user_entity")
    fun getSingleUser(): UserEntity

    @Query("UPDATE user_entity SET user_watched = :userWatched WHERE user_id = 1")
    fun updateUserWatchState(userWatched: Int)


    @Query("UPDATE user_entity SET user_language = :userLanguage WHERE user_id = 1")
    fun updateUserLanguage(userLanguage: String)
}