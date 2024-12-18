package com.bignerdranch.android.composepopuptrip.presentation.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bignerdranch.android.composepopuptrip.presentation.components.AppButton
import com.bignerdranch.android.composepopuptrip.presentation.components.LoginTitle
import com.bignerdranch.android.composepopuptrip.presentation.components.PopupDialog
import com.bignerdranch.android.composepopuptrip.presentation.components.TextInput

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
                navController.popBackStack()
                viewModel.resetState()
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

            LoginTitle("Reset Password")

            TextInput(
                value = email,
                onValueChange = { viewModel.updateEmail(it) },
                label = "Enter Email Address"
            )

            AppButton(
                onClick = { viewModel.resetPassword() },
                buttonText = "Send Reset Email"
            )

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

            TextButton(onClick = { navController.popBackStack() }) {
                Text("Back to Login")
            }
        }
    }
}