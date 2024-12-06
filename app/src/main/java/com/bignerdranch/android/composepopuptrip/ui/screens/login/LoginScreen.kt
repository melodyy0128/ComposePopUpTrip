package com.bignerdranch.android.composepopuptrip.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val mockNavController = rememberNavController() // Mock NavController for Preview
    val mockViewModel = LoginViewModel() // Mock ViewModel for Preview

    LoginScreen(navController = mockNavController, loginViewModel = mockViewModel)
}

@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel = viewModel()) {
    // Collect state from ViewModel
    val email by loginViewModel.email.collectAsState()
    val password by loginViewModel.password.collectAsState()
    val passwordVisibility by loginViewModel.passwordVisibility.collectAsState()
    val navigateToHome by loginViewModel.navigateToHome.collectAsState()
    val loginSuccess by loginViewModel.loginSuccess.collectAsState()
    val errorMessage by loginViewModel.errorMessage.collectAsState()

    // Handle navigation to home on successful login
    LaunchedEffect(loginSuccess) {
        if (loginSuccess) {
            navController.navigate("home/$email")
            loginViewModel.resetLoginState() // Reset the login state
        }
    }

    // Parent layout for the login screen
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {

            // Logo/Image
            LoginImage()

            // Title
            LoginTitle()

            // Email Input
            EmailInput(
                email = email,
                onEmailChange = { loginViewModel.updateEmail(it) }
            )

            // Password Input
            PasswordInput(
                password = password,
                passwordVisibility = passwordVisibility,
                onPasswordChange = { loginViewModel.updatePassword(it) },
                onPasswordVisibilityToggle = { loginViewModel.togglePasswordVisibility() }
            )

            // Login Button
            LoginButton(onClick = {
                loginViewModel.performLogin() // Perform login and trigger navigation
            })

            // Display error message only if it's not null
            errorMessage?.let { message ->
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // Create Account and Reset Password Actions
            AccountActions(
                onCreateAccount = { /* Navigate to Create Account Screen */ },
                onResetPassword = { /* Navigate to Reset Password Screen */ }
            )
        }
    }
}
