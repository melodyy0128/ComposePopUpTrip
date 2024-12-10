package com.bignerdranch.android.composepopuptrip.ui.screens.saved

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bignerdranch.android.composepopuptrip.data.model.Place

@Preview(showBackground = true)
@Composable
fun SavedPlacesScreenPreview() {
    val viewModel = SavedPlacesViewModel()
    SavedPlacesScreen(viewModel = viewModel)
}

@Composable
fun SavedPlacesScreen(viewModel: SavedPlacesViewModel = viewModel()) {

    val savedPlaces by viewModel.savedPlaces.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(savedPlaces) { place ->
                SavedPlaceItem(place = place)
            }
        }
    }
}

@Composable
fun SavedPlaceItem(place: Place) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = place.name, style = MaterialTheme.typography.titleMedium)

            Text(
                text = place.description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                text = "View Details",
                style = MaterialTheme.typography.bodySmall.copy(
                    textDecoration = TextDecoration.Underline
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable { /* Handle details navigation or actions */ }
            )
        }
    }
}