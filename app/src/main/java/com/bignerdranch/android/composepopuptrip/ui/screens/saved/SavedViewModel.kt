package com.bignerdranch.android.composepopuptrip.ui.screens.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SavedViewModel : ViewModel() {

    private val _navigateToSavedPlaces = MutableStateFlow(false)
    val navigateToSavedPlaces: StateFlow<Boolean> get() = _navigateToSavedPlaces

    private val _navigateToSavedRoutes = MutableStateFlow(false)
    val navigateToSavedRoutes: StateFlow<Boolean> get() = _navigateToSavedRoutes

    fun navigateToSavedPlaces() {
        _navigateToSavedPlaces.value = true
    }

    fun navigateToSavedRoutes() {
        _navigateToSavedRoutes.value = true
    }

    fun onNavigateToSavedPlacesComplete() {
        _navigateToSavedPlaces.value = false
    }

    fun onNavigateToSavedRoutesComplete() {
        _navigateToSavedRoutes.value = false
    }

}