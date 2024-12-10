package com.bignerdranch.android.composepopuptrip.ui.screens.saved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bignerdranch.android.composepopuptrip.ui.components.AppButton

@Composable
fun SavedScreen(navController: NavController, viewModel: SavedViewModel = viewModel()) {

    val navigateToSavedPlaces by viewModel.navigateToSavedPlaces.collectAsState()
    val navigateToSavedRoutes by viewModel.navigateToSavedRoutes.collectAsState()

    LaunchedEffect(navigateToSavedPlaces) {
        if (navigateToSavedPlaces) {
            navController.navigate("savedPlaces")
            viewModel.onNavigateToSavedPlacesComplete()
        }
    }

    LaunchedEffect(navigateToSavedRoutes) {
        if (navigateToSavedRoutes) {
            navController.navigate("savedRoutes")
            viewModel.onNavigateToSavedRoutesComplete()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            AppButton(onClick = { viewModel.navigateToSavedPlaces() }, buttonText = "Saved Places")

            AppButton(onClick = { viewModel.navigateToSavedRoutes() }, buttonText = "Saved Routes")
        }
    }

}