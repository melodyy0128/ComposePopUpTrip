package com.bignerdranch.android.composepopuptrip.data.repository

import com.bignerdranch.android.composepopuptrip.data.DaoObjects.UserDao
import com.bignerdranch.android.composepopuptrip.data.entities.PlaceType
import com.bignerdranch.android.composepopuptrip.data.entities.User

class UserRepository(private val userDao: UserDao) {
    suspend fun getUserByEmail(email: String): User? = userDao.getUserByEmail(email)

    suspend fun insertUser(user: User) = userDao.insertUser(user)

    suspend fun updateUser(email: String, username: String, placeTypes: List<PlaceType>) {
        val user = User(email = email, username = username, placeTypes = placeTypes)
        userDao.insertUser(user)
    }
}
