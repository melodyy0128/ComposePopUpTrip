package com.bignerdranch.android.composepopuptrip.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.composepopuptrip.data.entities.PlaceType
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

    private val _categorizedPlaceTypes = MutableStateFlow<Map<String, List<PlaceType>>>(emptyMap())
    val categorizedPlaceTypes: StateFlow<Map<String, List<PlaceType>>> get() = _categorizedPlaceTypes

    fun fetchUser(email: String) {
        viewModelScope.launch {
            val user = repository.getUserByEmail(email)
            _user.value = user

            val categorizedPlaceTypes = user?.placeTypes?.groupBy { it.category }
            _categorizedPlaceTypes.value = categorizedPlaceTypes ?: emptyMap()
            // Log the place types
//            categorizedPlaceTypes?.forEach { (category, placeTypes) ->
//                placeTypes.forEach { placeType ->
//                    println("Category: $category, PlaceType: $placeType")
//                }
//            }

        }
    }

    fun updateUserProfile(email: String, username: String, placeTypes: List<PlaceType>) {
        viewModelScope.launch {
            repository.updateUser(email, username, placeTypes)
            fetchUser(email) // Refresh the user data
        }
    }
}