package com.bignerdranch.android.composepopuptrip.presentation.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.composepopuptrip.data.DaoObjects.UserDao
import com.bignerdranch.android.composepopuptrip.data.entities.User
import com.bignerdranch.android.composepopuptrip.data.repository.AuthRepository
import com.bignerdranch.android.composepopuptrip.data.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

private const val TAG = "LoginViewModel"

class LoginViewModel(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

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

    private val _navigateToCompleteProfile = MutableStateFlow(false)
    val navigateToCompleteProfile: StateFlow<Boolean> get() = _navigateToCompleteProfile

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
                val loginSuccess = authRepository.loginWithEmailAndPassword(_email.value, _password.value)
                if (loginSuccess) {
                    Log.d(TAG, "performLogin: Login successful")
                    val user = userRepository.getUserByEmail(_email.value)
                    _navigateToCompleteProfile.value = user == null
                    _loginSuccess.value = true
                } else {
                    _errorMessage.value = "Login failed. Please try again."
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
        _navigateToCompleteProfile.value = false
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }

    fun resetLoginState() {
        _loginSuccess.value = false
        _errorMessage.value = null
    }
}