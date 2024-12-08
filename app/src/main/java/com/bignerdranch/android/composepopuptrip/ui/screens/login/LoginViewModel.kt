package com.bignerdranch.android.composepopuptrip.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> get() = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> get() = _password

    private val _passwordVisibility = MutableStateFlow(false)
    val passwordVisibility: StateFlow<Boolean> get() = _passwordVisibility

    private val _navigateToSignUp = MutableStateFlow(false)
    val navigateToSignUp: StateFlow<Boolean> get() = _navigateToSignUp

    private val _navigateToReset = MutableStateFlow(false)
    val navigateToReset: StateFlow<Boolean> get() = _navigateToReset

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> get() = _loginSuccess

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun togglePasswordVisibility() {
        _passwordVisibility.value = !_passwordVisibility.value
    }

    fun performLogin() {
        if (_email.value.isNotEmpty() && _password.value.isNotEmpty()) {
            viewModelScope.launch {
                auth.signInWithEmailAndPassword(_email.value, _password.value)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            _loginSuccess.value = true
                        } else {
                            _errorMessage.value = "Login failed. Please try again."
                        }
                    }
            }
        } else {
            _errorMessage.value = "Email and Password cannot be empty"
        }
    }

    fun navigateToSignUpScreen() {
        _navigateToSignUp.value = true
    }

    fun navigateToSetScreen() {
        _navigateToReset.value = true
    }

    fun resetNavigationState() {
        _navigateToSignUp.value = false
        _navigateToReset.value = false
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }

    fun resetLoginState() {
        _loginSuccess.value = false
        _errorMessage.value = null
    }
}