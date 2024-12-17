package com.bignerdranch.android.composepopuptrip.data.repository

import com.bignerdranch.android.composepopuptrip.data.DaoObjects.UserDao
import com.bignerdranch.android.composepopuptrip.data.entities.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    suspend fun getUserByEmail(email: String): User? = userDao.getUserByEmail(email)
    suspend fun insertUser(user: User) = userDao.insertUser(user)
}
