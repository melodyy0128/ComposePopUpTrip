package com.bignerdranch.android.composepopuptrip.data.model

data class Place(
    val id: String,              // Unique identifier for the place
    val name: String,            // Name of the place
    val description: String,     // Short description of the place
    val latitude: Double,        // Latitude for location
    val longitude: Double,       // Longitude for location
    val imageUrl: String? = null // Optional image URL for the place
)