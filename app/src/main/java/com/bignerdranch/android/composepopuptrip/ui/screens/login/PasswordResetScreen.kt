package com.bignerdranch.android.composepopuptrip.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun PasswordResetScreen(navController: NavController, viewModel: PasswordResetViewModel = viewModel()) {

    val email by viewModel.email.collectAsState()
    val resetSuccess by viewModel.resetSuccess.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val showPopup by viewModel.showPopup.collectAsState()

    LaunchedEffect(resetSuccess) {
        if (resetSuccess) {
            viewModel.showPopup()
        }
    }

    if (showPopup) {
        PopupDialog(
            onDismiss = {
                viewModel.hidePopup()
                navController.popBackStack() // Navigates back to the previous screen
                viewModel.resetState() // Reset the state after navigation
            },
            text = "Reset Email Sent!",
            buttonText = "OK"
        )
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

            // Title
            LoginTitle("Reset Password")

            // Email Input Field (Reusing TextInput)
            TextInput(
                value = email,
                onValueChange = { viewModel.updateEmail(it) },
                label = "Enter Email Address"
            )

            // Reset Button (Reusing LoginButton with custom text)
            LoginButton(
                onClick = { viewModel.resetPassword() },
                buttonText = "Send Reset Email"
            )

            // Error Message
            errorMessage?.let { message ->
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 8.dp)
                )
            }

            // Back to Login Button (Reusing AccountActions)
            TextButton(onClick = { navController.popBackStack() }) {
                Text("Back to Login")
            }
        }
    }
}