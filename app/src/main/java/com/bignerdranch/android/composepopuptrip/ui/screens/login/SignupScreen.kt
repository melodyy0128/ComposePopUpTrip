package com.bignerdranch.android.composepopuptrip.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlin.math.sign

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    val mockNavController = rememberNavController() // Mock NavController for preview
    val mockViewModel = SignUpViewModel() // Create an instance of SignUpViewModel

    // Mock some initial states if necessary
    mockViewModel.updateUserName("MockUser")
    mockViewModel.updateEmail("mock@example.com")

    // Call the SignUpScreen with mocked dependencies
    SignUpScreen(
        navController = mockNavController,
        viewModel = mockViewModel
    )
}


@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel = viewModel()) {

    val userName by viewModel.userName.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()
    val passwordVisibility by viewModel.passwordVisibility.collectAsState()
    val signUpSuccess by viewModel.signUpSuccess.collectAsState()
    val navigateToLogin by viewModel.navigateToLogin.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val showPopup by viewModel.showAccountCreatedPopup.collectAsState()

    // Handle navigation on successful sign-up
    LaunchedEffect(signUpSuccess) {
        if (signUpSuccess) {
            viewModel.resetSignUpState()
            viewModel.showAccountCreatedPopup()
        }
    }

    if (showPopup) {
        PopupDialog(
            onDismiss = {
                viewModel.hideAccountCreatedPopup()
                navController.navigate("login") // Navigate to Login screen after dismiss
            },
            text = "Account Created!",
            buttonText = "OK"
        )
    }

    LaunchedEffect(navigateToLogin) {
        if (navigateToLogin) {
            navController.navigate("login") {
                popUpTo("signup") { inclusive = true }
            }
            viewModel.resetNavigationState()
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

            // Title
            LoginTitle("Sign Up")

            // Username Input
            TextInput(value = userName, onValueChange = { viewModel.updateUserName(it) }, label = "Enter User Name")

            // Email Input
            TextInput(value = email, onValueChange = { viewModel.updateEmail(it) }, label = "Enter Email")

            // Password Input
            PasswordInput(
                password = password,
                passwordVisibility = passwordVisibility,
                onPasswordChange = { viewModel.updatePassword(it) },
                onPasswordVisibilityToggle = { viewModel.togglePasswordVisibility() }
            )

            // Confirm Password Input
            PasswordInput(
                password = confirmPassword,
                passwordVisibility = passwordVisibility,
                onPasswordChange = { viewModel.updateConfirmPassword(it) },
                onPasswordVisibilityToggle = { viewModel.togglePasswordVisibility() }
            )

            // Sign-Up Button
            LoginButton(
                onClick = { viewModel.performSignUp() },
                buttonText = "Sign Up"
            )

            TextButton(onClick = { viewModel.navigateToLoginScreen() }) {
                Text(text = "Already have an account? Login")
            }

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
        }
    }



}
