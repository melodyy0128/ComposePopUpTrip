package com.bignerdranch.android.composepopuptrip.presentation.screens.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel : ViewModel() {

    private val _navigateToSignIn = MutableStateFlow(false)
    val navigateToSignIn: StateFlow<Boolean> get() = _navigateToSignIn

    fun navigateToSignIn() {
        _navigateToSignIn.value = true
    }

    fun navigateToSignInComplete() {
        _navigateToSignIn.value = false
    }


}