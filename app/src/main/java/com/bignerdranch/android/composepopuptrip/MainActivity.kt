package com.bignerdranch.android.composepopuptrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bignerdranch.android.composepopuptrip.data.repository.GooglePlacesRepository
import com.bignerdranch.android.composepopuptrip.presentation.NavigationGraph
import com.bignerdranch.android.composepopuptrip.presentation.screens.home.HomeViewModel
import com.bignerdranch.android.composepopuptrip.presentation.theme.ComposePopUpTripTheme
import com.google.android.libraries.places.api.Places

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val placesClient = Places.createClient(this)

        // Create Repository and ViewModel
        val placesRepository = GooglePlacesRepository(placesClient)
        val homeViewModel = HomeViewModel(placesRepository)

        setContent {
            ComposePopUpTripTheme {
                NavigationGraph(homeViewModel = homeViewModel)
            }
        }
    }
}
