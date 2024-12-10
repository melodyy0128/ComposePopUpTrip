package com.bignerdranch.android.composepopuptrip.data.model


//make this user class an entity
//add the entity annotation

data class User(
    val id: String,
    val name: String,
    val email: String,
    val profileImageUrl: String? = null
)