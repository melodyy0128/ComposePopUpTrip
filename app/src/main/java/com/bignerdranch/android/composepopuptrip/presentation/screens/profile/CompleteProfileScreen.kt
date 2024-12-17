package com.bignerdranch.android.composepopuptrip.presentation.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bignerdranch.android.composepopuptrip.presentation.components.AppButton
import com.bignerdranch.android.composepopuptrip.presentation.components.LoginTitle
import com.bignerdranch.android.composepopuptrip.presentation.components.ProfilePlaceTypeSelector

@Composable
fun CompleteProfileScreen(
    email: String,
    navController: NavController,
    viewModel: CompleteProfileViewModel
) {

    val usernameInput by viewModel.username.collectAsState()
    val selectedPlaceTypes by viewModel.placeTypes.collectAsState()
    val navigateToHome by viewModel.navigateToHome.collectAsState()

    var inputText by remember { mutableStateOf(usernameInput) }

    val placeTypes = listOf("Restaurant", "Park", "Museum", "Cafe", "Gym", "Beach", "Library")

    LaunchedEffect(navigateToHome) {
        if (navigateToHome) {
            navController.navigate("home")
            viewModel.navigateToHomeComplete()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            LoginTitle("Complete Profile")

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                value = email,
                onValueChange = {},
                enabled = false,
                label = { Text("Email") }
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                value = inputText,
                onValueChange = { newValue: String -> inputText = newValue },
                label = { Text("Username") }
            )

            // Place Types Selector
            Text(
                text = "Select Your Place Preferences:",
                style = MaterialTheme.typography.bodyMedium
            )

            ProfilePlaceTypeSelector(
                placeTypes = placeTypes,
                selectedTypes = selectedPlaceTypes,
                onSelectionChanged = { updatedTypes ->
                    viewModel.updatePlaceTypes(updatedTypes)
                },
                modifier = Modifier.fillMaxWidth()
            )

            AppButton(
                onClick = {
                    viewModel.updateUsername(inputText)
                    viewModel.completeProfile(email)
                },
                buttonText = "Complete Profile"
            )

        }
    }


}