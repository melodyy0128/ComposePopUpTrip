package com.bignerdranch.android.composepopuptrip.presentation.screens.profile

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bignerdranch.android.composepopuptrip.data.entities.PlaceType
import com.bignerdranch.android.composepopuptrip.data.entities.User
import com.bignerdranch.android.composepopuptrip.presentation.SharedViewModel
import com.bignerdranch.android.composepopuptrip.presentation.components.ProfilePlaceTypeSelector

private const val TAG = "ProfileScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    sharedViewModel: SharedViewModel
) {
    val userState by viewModel.user.collectAsState()
    val email by sharedViewModel.email.collectAsState()
    val allPlaceTypes = PlaceType.entries.groupBy { it.category }
//    val categorizedPlaceTypes by viewModel.categorizedPlaceTypes.collectAsState()
    var isEditing by remember { mutableStateOf(false) }
    var updatedUsername by remember { mutableStateOf("") }
    var updatedPlaceTypes by remember { mutableStateOf<List<PlaceType>>(emptyList()) }
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(email) {
        email?.let {
            viewModel.fetchUser(it)
        }
    }

    LaunchedEffect(userState) {
        userState?.let { user ->
            updatedPlaceTypes = user.placeTypes
            updatedUsername = user.username ?: ""
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                actions = {
                    TextButton(
                        onClick = {
                            if (isEditing) {
                                // Trigger ViewModel validation and updates
                                viewModel.updateUserProfile(
                                    email = email ?: "",
                                    username = updatedUsername,
                                    placeTypes = updatedPlaceTypes
                                )

                                // Check for errors; only exit editing mode if no errors
                                if (viewModel.errorMessage.value.isNullOrEmpty()) {
                                    isEditing = false
                                }
                            } else {
                                // Enter editing mode
                                isEditing = true
                                viewModel.clearErrorMessage() // Clear any previous error messages
                            }
                        }
                    ) {
                        // Button text depends on `isEditing`, but `isEditing` changes only after validation
                        Text(if (isEditing) "DONE" else "EDIT")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (userState == null) {
                CircularProgressIndicator()
            } else {
                ProfileContent(
                    user = userState!!,
                    allPlaceTypes = allPlaceTypes,
                    isEditing = isEditing,
                    updatedUsername = updatedUsername,
                    updatedPlaceTypes = updatedPlaceTypes,
                    onUsernameChange = { updatedUsername = it },
                    onPlaceTypesChange = { updatedPlaceTypes = it },
                    errorMessage = errorMessage
                )
            }
        }
    }
}

@Composable
fun ProfileContent(
    user: User,
    allPlaceTypes: Map<String, List<PlaceType>>,
    isEditing: Boolean,
    updatedUsername: String,
    updatedPlaceTypes: List<PlaceType>,
    onUsernameChange: (String) -> Unit,
    onPlaceTypesChange: (List<PlaceType>) -> Unit,
    errorMessage: String?
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {

        TextField(
            value = user.email,
            onValueChange = {},
            label = { Text("Email") },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )

        if (isEditing) {
            TextField(
                value = updatedUsername,
                onValueChange = onUsernameChange,
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            TextField(
                value = user.username ?: "User",
                onValueChange = {},
                label = { Text("Username") },
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            )
        }

        allPlaceTypes.forEach { (category, placeTypes) ->
            Column {
                Text(
                    text = category,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                ProfilePlaceTypeSelector(
                    placeTypes = placeTypes,
                    selectedTypes = if (isEditing) updatedPlaceTypes else user.placeTypes,
                    onSelectionChanged = {
                        if (isEditing) onPlaceTypesChange(it)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        errorMessage?.let { message ->
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

    }
}