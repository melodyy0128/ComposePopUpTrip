package com.bignerdranch.android.composepopuptrip.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel:  ViewModel(){
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // State variables for user input
    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> get() = _userName

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> get() = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> get() = _password

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> get() = _confirmPassword

    private val _passwordVisibility = MutableStateFlow(false)
    val passwordVisibility: StateFlow<Boolean> get() = _passwordVisibility

    // State for sign-up result
    private val _signUpSuccess = MutableStateFlow(false)
    val signUpSuccess: StateFlow<Boolean> get() = _signUpSuccess

    private val _navigateToLogin = MutableStateFlow(false)
    val navigateToLogin: StateFlow<Boolean> get() = _navigateToLogin

    // State for error messages
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    private val _showAccountCreatedPopup = MutableStateFlow(false)
    val showAccountCreatedPopup: StateFlow<Boolean> get() = _showAccountCreatedPopup



    // Update input fields
    fun updateUserName(name: String) {
        _userName.value = name
    }

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun updateConfirmPassword(newPassword: String) {
        _confirmPassword.value = newPassword
    }

    // Toggle password visibility
    fun togglePasswordVisibility() {
        _passwordVisibility.value = !_passwordVisibility.value
    }

    // Perform sign-up
    fun performSignUp() {
        if (_email.value.isEmpty() || _password.value.isEmpty() || _userName.value.isEmpty()) {
            _errorMessage.value = "All fields are required"
            return
        }

        if (_password.value != _confirmPassword.value) {
            _errorMessage.value = "Passwords do not match"
            return
        }

        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(_email.value, _password.value)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _signUpSuccess.value = true
                    } else {
                        _errorMessage.value = task.exception?.message ?: "Sign-up failed"
                    }
                }
        }
    }

    fun navigateToLoginScreen() {
        _navigateToLogin.value = true
    }

    fun showAccountCreatedPopup() {
        _showAccountCreatedPopup.value = true
    }

    fun hideAccountCreatedPopup() {
        _showAccountCreatedPopup.value = false
    }

    // Reset the state after handling
    fun resetSignUpState() {
        _signUpSuccess.value = false
        _errorMessage.value = null
        hideAccountCreatedPopup()
    }

    fun resetNavigationState() {
        _navigateToLogin.value = false
        _signUpSuccess.value = false
    }
}