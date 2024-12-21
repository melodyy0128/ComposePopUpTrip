package com.bignerdranch.android.composepopuptrip.presentation.screens.profile

import android.util.Log
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
import com.bignerdranch.android.composepopuptrip.data.entities.PlaceType
import com.bignerdranch.android.composepopuptrip.presentation.components.AppButton
import com.bignerdranch.android.composepopuptrip.presentation.components.LoginTitle
import com.bignerdranch.android.composepopuptrip.presentation.components.ProfilePlaceTypeSelector

private const val TAG = "CompleteProfileScreen"

@Composable
fun CompleteProfileScreen(
    email: String,
    navController: NavController,
    viewModel: CompleteProfileViewModel
) {

    val usernameInput by viewModel.username.collectAsState()
    val selectedPlaceTypes by viewModel.selectedPlaceTypes.collectAsState()
    val navigateToHome by viewModel.navigateToHome.collectAsState()

    var inputText by remember { mutableStateOf(usernameInput) }

    val placeTypesByCategory = PlaceType.entries.groupBy { it.category }
    // Log the placeTypesByCategory
     Log.d(TAG, "CompleteProfileScreen: placeTypesByCategory: $placeTypesByCategory")

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
                style = MaterialTheme.typography.titleMedium
            )

            placeTypesByCategory.forEach { (category, placeTypes) ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = category,
                        style = MaterialTheme.typography.titleSmall,
//                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    ProfilePlaceTypeSelector(
                        placeTypes = placeTypes,
                        selectedTypes = selectedPlaceTypes,
                        onSelectionChanged = { updatedTypes ->
                            viewModel.updatePlaceTypes(updatedTypes)
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

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