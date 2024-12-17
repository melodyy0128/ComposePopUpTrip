package com.bignerdranch.android.composepopuptrip.data.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var userDatabaseInstance: UserDatabase? = null

    fun getUserDatabase(context: Context): UserDatabase {
        return userDatabaseInstance ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                "user_database"
            ).build()
            userDatabaseInstance = instance
            instance
        }
    }
}