package com.bignerdranch.android.composepopuptrip.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.composepopuptrip.data.DaoObjects.UserDao
import com.bignerdranch.android.composepopuptrip.data.entities.User
import com.bignerdranch.android.composepopuptrip.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user

    fun fetchUser(email: String) {
        viewModelScope.launch {
            _user.value = repository.getUserByEmail(email)
        }
    }
}