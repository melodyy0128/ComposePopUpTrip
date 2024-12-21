package com.bignerdranch.android.composepopuptrip.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.composepopuptrip.data.entities.PlaceType
import com.bignerdranch.android.composepopuptrip.data.entities.User
import com.bignerdranch.android.composepopuptrip.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CompleteProfileViewModel(private val repository: UserRepository) : ViewModel() {

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> get() = _username

    private val _selectedPlaceTypes = MutableStateFlow(emptyList<PlaceType>())
    val selectedPlaceTypes: StateFlow<List<PlaceType>> get() = _selectedPlaceTypes

    private val _navigateToHome = MutableStateFlow(false)
    val navigateToHome: StateFlow<Boolean> get() = _navigateToHome

    fun updateUsername(newUsername: String) {
        _username.value = newUsername
    }

    fun updatePlaceTypes(newPlaceTypes: List<PlaceType>) {
        _selectedPlaceTypes.value = newPlaceTypes
    }

    fun completeProfile(email: String) {
        if (_username.value.isNotEmpty()) {
            viewModelScope.launch {
                val user = User(
                    email = email,
                    username = _username.value,
                    placeTypes = _selectedPlaceTypes.value
                )
                repository.insertUser(user)
                _navigateToHome.value = true
            }
        }
    }

    fun navigateToHomeComplete() {
        _navigateToHome.value = false
    }
}