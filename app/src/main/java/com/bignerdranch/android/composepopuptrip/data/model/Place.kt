package com.bignerdranch.android.composepopuptrip.data.model

data class Place(
    val id: String,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val imageUrl: String? = null
)