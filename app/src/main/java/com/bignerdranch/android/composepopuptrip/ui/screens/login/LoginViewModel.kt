package com.bignerdranch.android.composepopuptrip.ui.screens.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {

    // State for email input
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> get() = _email

    // State for password input
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> get() = _password

    // State for password visibility
    private val _passwordVisibility = MutableStateFlow(false)
    val passwordVisibility: StateFlow<Boolean> get() = _passwordVisibility

    private val _navigateToHome = MutableStateFlow<String?>(null)
    val navigateToHome: StateFlow<String?> get() = _navigateToHome

    // Functions to update email and password
    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    // Function to toggle password visibility
    fun togglePasswordVisibility() {
        _passwordVisibility.value = !_passwordVisibility.value
    }

    // Login validation logic (can be replaced with API integration)
    fun performLogin() {
        if (_email.value.isNotEmpty() && _password.value.isNotEmpty()) {
            // Trigger navigation to home with the email
            _navigateToHome.value = _email.value
        }
    }

    fun onNavigateToHomeHandled() {
        _navigateToHome.value = null
    }
}