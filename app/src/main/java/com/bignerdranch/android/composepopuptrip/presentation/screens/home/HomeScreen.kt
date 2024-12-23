package com.bignerdranch.android.composepopuptrip.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bignerdranch.android.composepopuptrip.presentation.components.ActivateBtn
import com.bignerdranch.android.composepopuptrip.presentation.components.DropdownSearchBar
import com.bignerdranch.android.composepopuptrip.presentation.components.LoginTitle

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel) {
    val startQuery by homeViewModel.startQuery.collectAsState()
    val destinationQuery by homeViewModel.destQuery.collectAsState()
    val suggestions by homeViewModel.suggestions.collectAsState()

    val isBtnEnabled = startQuery.isNotBlank() && destinationQuery.isNotBlank()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        LoginTitle("HOME")

        Spacer(modifier = Modifier.padding(8.dp))

        DropdownSearchBar(
            query = startQuery,
            onQueryChange = { homeViewModel.updateStartQuery(it) },
            suggestions = suggestions,
            onSuggestionClick = { suggestion ->
                println("Selected Starting Point: $suggestion")
            },
            "Starting Point",
            navController = navController
        )

        DropdownSearchBar(
            query = destinationQuery,
            onQueryChange = { homeViewModel.updateQuery(it) },
            suggestions = suggestions,
            onSuggestionClick = { suggestion ->
                println("Selected place: $suggestion")
            },
            "Destination",
            navController = navController
        )

        Spacer(modifier = Modifier.height(8.dp))

        ActivateBtn(
            onClick = {
                navController.navigate("home/routeMap/${startQuery.trim()}/${destinationQuery.trim()}")
            },
            buttonText = "NEXT",
            enabled = isBtnEnabled
        )
    }
}
