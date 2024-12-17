package com.bignerdranch.android.composepopuptrip.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// TAG for logging
private const val TAG = "SharedViewModel"

class SharedViewModel : ViewModel() {
    private val _email = MutableStateFlow<String?>(null)
//    val email: StateFlow<String?> get() = _email

    val email: StateFlow<String?>
        get() {
            Log.d(TAG, "Email StateFlow accessed")
            return _email
        }

    fun setEmail(userEmail: String) {
        _email.value = userEmail
        Log.d(TAG, "Email set to: $userEmail")
    }
}