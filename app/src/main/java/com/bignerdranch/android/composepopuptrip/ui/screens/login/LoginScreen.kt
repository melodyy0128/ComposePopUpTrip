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
    val mockNavController = rememberNavController()
    val mockViewModel = LoginViewModel()

    LoginScreen(navController = mockNavController, loginViewModel = mockViewModel)
}

@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel = viewModel()) {

    val email by loginViewModel.email.collectAsState()
    val password by loginViewModel.password.collectAsState()
    val passwordVisibility by loginViewModel.passwordVisibility.collectAsState()
    val loginSuccess by loginViewModel.loginSuccess.collectAsState()
    val errorMessage by loginViewModel.errorMessage.collectAsState()
    val navigateToSignUp by loginViewModel.navigateToSignUp.collectAsState()
    val navigateToReset by loginViewModel.navigateToReset.collectAsState()

    LaunchedEffect(loginSuccess) {
        if (loginSuccess) {
            navController.navigate("home/$email")
            loginViewModel.resetLoginState()
        }
    }

    LaunchedEffect(navigateToSignUp) {
        if (navigateToSignUp) {
            navController.navigate("signup")
            loginViewModel.resetNavigationState()
        }
    }

    LaunchedEffect(navigateToReset) {
        if (navigateToReset) {
            navController.navigate("resetPassword")
            loginViewModel.resetNavigationState()
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

            LoginImage()

            LoginTitle()

            TextInput(
                value = email,
                onValueChange = {
                    loginViewModel.updateEmail(it)
                    loginViewModel.clearErrorMessage()
                },
                label = "Enter Email"
            )

            PasswordInput(
                password = password,
                passwordVisibility = passwordVisibility,
                onPasswordChange = {
                    loginViewModel.updatePassword(it)
                    loginViewModel.clearErrorMessage() },
                onPasswordVisibilityToggle = { loginViewModel.togglePasswordVisibility() }
            )

            LoginButton(onClick = {
                loginViewModel.performLogin()
            })

            errorMessage?.let { message ->
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            AccountActions(
                onCreateAccount = { loginViewModel.navigateToSignUpScreen() },
                onResetPassword = { loginViewModel.navigateToSetScreen() }
            )
        }
    }
}
