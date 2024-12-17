package com.bignerdranch.android.composepopuptrip.data.entities

enum class PlaceType(val displayName: String) {
    RESTAURANT("Restaurant"),
    CAFE("Cafe"),
    BAKER("Bakery"),
    PARK("Park"),
    MUSEUM("Museum"),
    SHOPPING_MALL("Shopping Mall"),
    BAR("Bar"),
    LIBRARY("Library"),
    BOOK_STORE("Book Store"),
    CLOTHING_STORE("Clothing Store"),
    PET_STORE("Pet Store"),
    SHOE_STORE("Shoe Store"),
    FLORIST("Florist"),
    MOVIE_THEATER("Movie Theater"),
    NIGHT_CLUB("Night Club"),
    ZOO("Zoo"),
    AQUARIUM("Aquarium"),
    AMUSEMENT_PARK("Amusement Park"),
    TOURIST_ATTRACTION("Tourist Attraction"),
    ART_GALLERY("Art Gallery"),
    BEACH("Beach"),
    SPA("Spa");

    companion object {
        // Helper function to get enum from display name
        fun fromDisplayName(name: String): PlaceType? {
            return entries.find { it.displayName == name }
        }

        // List of all display names (for UI usage)
        val displayNames: List<String>
            get() = entries.map { it.displayName }
    }
}