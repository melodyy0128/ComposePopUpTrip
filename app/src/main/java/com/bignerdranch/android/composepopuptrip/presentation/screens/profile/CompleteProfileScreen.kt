package com.bignerdranch.android.composepopuptrip.presentation.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

@Composable
fun CompleteProfileScreen(
    email: String,
    navController: NavController,
    viewModel: CompleteProfileViewModel
) {


    val usernameInput by viewModel.username.collectAsState()

    val navigateToHome by viewModel.navigateToHome.collectAsState()

    var inputText by remember { mutableStateOf(usernameInput) }

    LaunchedEffect(navigateToHome) {
        if (navigateToHome) {
            navController.navigate("home")
            viewModel.navigateToHomeComplete()
        }
    }

    Column {
        TextField(
            value = email,
            onValueChange = {},
            enabled = false,
            label = { Text("Email") }
        )

        TextField(
            value = inputText,
            onValueChange = { newValue: String -> inputText = newValue },
            label = { Text("Username") }
        )


        Button(onClick = {
            viewModel.updateUsername(inputText)
            viewModel.completeProfile(email)
            }) {
            Text("Complete Profile")
        }
    }


}