package com.bignerdranch.android.composepopuptrip.data.entities

enum class PlaceType(val displayName: String, val category: String) {
    RESTAURANT("Restaurant", "Food & Drink"),
    CAFE("Cafe", "Food & Drink"),
    BAKER("Bakery", "Food & Drink"),
    PARK("Park", "Outdoors"),
    MUSEUM("Museum", "Culture"),
    SHOPPING_MALL("Shopping Mall", "Shops"),
    BAR("Bar", "Food & Drink"),
    LIBRARY("Library", "Culture"),
    BOOK_STORE("Book Store", "Shops"),
    CLOTHING_STORE("Clothing Store", "Shops"),
    PET_STORE("Pet Store", "Shops"),
    SHOE_STORE("Shoe Store", "Shops"),
    FLORIST("Florist", "Shops"),
    MOVIE_THEATER("Movie Theater", "Entertainment"),
    NIGHT_CLUB("Night Club", "Entertainment"),
    ZOO("Zoo", "Outdoors"),
    AQUARIUM("Aquarium", "Outdoors"),
    AMUSEMENT_PARK("Amusement Park", "Entertainment"),
    TOURIST_ATTRACTION("Tourist Attraction", "Culture"),
    ART_GALLERY("Art Gallery", "Culture"),
    BEACH("Beach", "Outdoors");

    companion object {
        // Get a list of all categories
        fun categories(): List<String> {
            return entries.map { it.category }.distinct()
        }

        // Get PlaceTypes by category
        fun getByCategory(category: String): List<PlaceType> {
            return entries.filter { it.category == category }
        }
    }
}
