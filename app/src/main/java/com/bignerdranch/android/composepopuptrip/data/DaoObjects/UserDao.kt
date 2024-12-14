package com.bignerdranch.android.composepopuptrip.data.DaoObjects

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import com.bignerdranch.android.composepopuptrip.data.entities.User

@Dao
interface UserDao {

    @Upsert
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)



}