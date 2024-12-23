package com.bignerdranch.android.composepopuptrip.data.database

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DatabaseProvider {

    @Volatile
    private var userDatabase: UserDatabase? = null

    /**
     * Provides a singleton instance of the UserDatabase. Uses coroutines to avoid blocking the main thread.
     */
    suspend fun provideUserDatabase(context: Context): UserDatabase {
        // Return if already initialized
        userDatabase?.let { return it }

        // Create database outside synchronized block
        val dbInstance = withContext(Dispatchers.IO) {
            Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                "user_database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        // Set the instance in a thread-safe way
        synchronized(this) {
            if (userDatabase == null) {
                userDatabase = dbInstance
            }
            return userDatabase!!
        }
    }
}
