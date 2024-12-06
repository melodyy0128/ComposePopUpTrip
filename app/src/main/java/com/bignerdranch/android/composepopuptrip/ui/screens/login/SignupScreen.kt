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
        signUpViewModel = mockViewModel
    )
}


@Composable
fun SignUpScreen(navController: NavController, signUpViewModel: SignUpViewModel = viewModel()) {

    val userName by signUpViewModel.userName.collectAsState()
    val email by signUpViewModel.email.collectAsState()
    val password by signUpViewModel.password.collectAsState()
    val confirmPassword by signUpViewModel.confirmPassword.collectAsState()
    val passwordVisibility by signUpViewModel.passwordVisibility.collectAsState()
    val signUpSuccess by signUpViewModel.signUpSuccess.collectAsState()
    val navigateToLogin by signUpViewModel.navigateToLogin.collectAsState()
    val errorMessage by signUpViewModel.errorMessage.collectAsState()
    val showPopup by signUpViewModel.showAccountCreatedPopup.collectAsState()

    // Handle navigation on successful sign-up
    LaunchedEffect(signUpSuccess) {
        if (signUpSuccess) {
            signUpViewModel.showAccountCreatedPopup()
        }
    }

    if (showPopup) {
        AccountCreatedDialog(
            onDismiss = {
                signUpViewModel.hideAccountCreatedPopup()
                navController.navigate("login") // Navigate to Login screen after dismiss
            }
        )
    }

//    LaunchedEffect(signUpSuccess) {
//        if (signUpSuccess) {
//            navController.navigate("login") // Navigate to login after successful sign-up
//            signUpViewModel.resetSignUpState()
//        }
//    }

    LaunchedEffect(navigateToLogin) {
        if (navigateToLogin) {
            navController.navigate("login") {
                popUpTo("signup") { inclusive = true }
            }
            signUpViewModel.resetNavigationState()
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
            // Logo
            LoginImage() // Reuse the LoginImage composable for the logo.

            // Title
            LoginTitle("Sign Up")

            // Username Input
            TextInput(value = userName, onValueChange = { signUpViewModel.updateUserName(it) }, label = "Enter User Name")

            // Email Input
            TextInput(value = email, onValueChange = { signUpViewModel.updateEmail(it) }, label = "Enter Email")

            // Password Input
            PasswordInput(
                password = password,
                passwordVisibility = passwordVisibility,
                onPasswordChange = { signUpViewModel.updatePassword(it) },
                onPasswordVisibilityToggle = { signUpViewModel.togglePasswordVisibility() }
            )

            // Confirm Password Input
            PasswordInput(
                password = confirmPassword,
                passwordVisibility = passwordVisibility,
                onPasswordChange = { signUpViewModel.updateConfirmPassword(it) },
                onPasswordVisibilityToggle = { signUpViewModel.togglePasswordVisibility() }
            )

            // Sign-Up Button
            LoginButton(
                onClick = { signUpViewModel.performSignUp() },
                buttonText = "Sign Up"
            )

            TextButton(onClick = { signUpViewModel.navigateToLoginScreen() }) {
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
