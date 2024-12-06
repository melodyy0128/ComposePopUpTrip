package com.bignerdranch.android.composepopuptrip.data.model

data class User(
    val id: String,              // Unique identifier for the user
    val name: String,            // Full name of the user
    val email: String,           // Email address of the user
    val profileImageUrl: String? = null // Optional profile image URL
)