package com.bignerdranch.android.composepopuptrip.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PasswordResetViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> get() = _email

    private val _resetSuccess = MutableStateFlow(false)
    val resetSuccess: StateFlow<Boolean> get() = _resetSuccess

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    private val _showPopup = MutableStateFlow(false)
    val showPopup: StateFlow<Boolean> get() = _showPopup

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun resetPassword() {
        if (_email.value.isEmpty()) {
            _errorMessage.value = "Email field cannot be empty"
            return
        }

        viewModelScope.launch {
            auth.sendPasswordResetEmail(_email.value)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _resetSuccess.value = true
                        _errorMessage.value = null
                    } else {
                        _resetSuccess.value = false
                        _errorMessage.value = task.exception?.message ?: "Failed to send reset email"
                    }
                }
        }
    }

    fun showPopup() {
        _showPopup.value = true
    }

    fun hidePopup() {
        _showPopup.value = false
    }

    fun resetState() {
        _resetSuccess.value = false
        _errorMessage.value = null
    }
}
