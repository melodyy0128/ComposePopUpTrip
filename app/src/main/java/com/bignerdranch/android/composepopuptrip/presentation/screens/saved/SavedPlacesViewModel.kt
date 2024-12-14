package com.bignerdranch.android.composepopuptrip.presentation.screens.saved

import androidx.lifecycle.ViewModel
import com.bignerdranch.android.composepopuptrip.data.entities.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SavedPlacesViewModel : ViewModel() {

    // Temporary list of saved places for testing
    private val _savedPlaces = MutableStateFlow(
        listOf(
            Place(
                id = "1",
                name = "Central Park",
                description = "A large public park in New York City.",
                latitude = 40.785091,
                longitude = -73.968285
            ),
            Place(
                id = "2",
                name = "Eiffel Tower",
                description = "An iconic landmark in Paris, France.",
                latitude = 48.858844,
                longitude = 2.294351
            ),
            Place(
                id = "3",
                name = "Great Wall of China",
                description = "A historic wall in China.",
                latitude = 40.431908,
                longitude = 116.570374
            )
        )
    )
    val savedPlaces: StateFlow<List<Place>> = _savedPlaces

    fun addPlace(place: Place) {
        _savedPlaces.value += place
    }

    fun removePlace(place: Place) {
        _savedPlaces.value -= place
    }


}