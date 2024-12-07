package com.bignerdranch.android.composepopuptrip.data.repository

import android.util.Log
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient

class GooglePlacesRepository(private val placesClient: PlacesClient) {

    fun fetchPlaceSuggestions(
        query: String,
        onSuccess: (List<String>) -> Unit,
        onError: (Exception) -> Unit
    ) {
        val request = FindAutocompletePredictionsRequest.builder()
            .setQuery(query)
            .build()

        placesClient.findAutocompletePredictions(request)
            .addOnSuccessListener { response ->
                val suggestions = response.autocompletePredictions.map {
                    it.getFullText(null).toString()
                }

//                Log.d("GooglePlacesRepository", "Suggestions: $suggestions")
                onSuccess(suggestions)
            }
            .addOnFailureListener { exception ->

//                Log.e("GooglePlacesRepository", "Error fetching suggestions", exception)
                onError(exception)
            }
    }
}
