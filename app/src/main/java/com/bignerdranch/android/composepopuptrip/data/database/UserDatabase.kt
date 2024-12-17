package com.bignerdranch.android.composepopuptrip.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bignerdranch.android.composepopuptrip.data.DaoObjects.UserDao
import com.bignerdranch.android.composepopuptrip.data.entities.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}