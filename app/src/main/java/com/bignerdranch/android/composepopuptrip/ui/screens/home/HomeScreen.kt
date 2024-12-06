package com.bignerdranch.android.composepopuptrip.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bignerdranch.android.composepopuptrip.ui.components.DropdownSearchBar
import com.bignerdranch.android.composepopuptrip.ui.components.LoginTitle

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    val query by homeViewModel.query.collectAsState()
    val suggestions by homeViewModel.suggestions.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Screen title
        LoginTitle("HOME")

        // Search Bar with Autocomplete
        DropdownSearchBar(
            query = query,
            onQueryChange = { homeViewModel.updateQuery(it) },
            suggestions = suggestions,
            onSuggestionClick = { suggestion ->
                println("Selected place: $suggestion")
            }
        )
    }
}
