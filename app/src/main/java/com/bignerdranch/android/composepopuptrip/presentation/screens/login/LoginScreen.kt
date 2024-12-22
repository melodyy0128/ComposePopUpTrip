package com.bignerdranch.android.composepopuptrip.presentation.screens.login

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bignerdranch.android.composepopuptrip.presentation.SharedViewModel
import com.bignerdranch.android.composepopuptrip.presentation.components.AccountActions
import com.bignerdranch.android.composepopuptrip.presentation.components.AppButton
import com.bignerdranch.android.composepopuptrip.presentation.components.LoginImage
import com.bignerdranch.android.composepopuptrip.presentation.components.LoginTitle
import com.bignerdranch.android.composepopuptrip.presentation.components.PasswordInput
import com.bignerdranch.android.composepopuptrip.presentation.components.TextInput

private const val TAG = "LoginScreen"

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel,
    sharedViewModel: SharedViewModel
) {

    val email by loginViewModel.email.collectAsState()
    val password by loginViewModel.password.collectAsState()
    val passwordVisibility by loginViewModel.passwordVisibility.collectAsState()
    val loginSuccess by loginViewModel.loginSuccess.collectAsState()
    val navigateToCompleteProfile by loginViewModel.navigateToCompleteProfile.collectAsState()
    val errorMessage by loginViewModel.errorMessage.collectAsState()
    val navigateToSignUp by loginViewModel.navigateToSignUp.collectAsState()
    val navigateToReset by loginViewModel.navigateToReset.collectAsState()


    LaunchedEffect(loginSuccess) {
        if (navigateToCompleteProfile) {
            loginViewModel.resetNavigationState()
            sharedViewModel.setEmail(email.trim())
            Log.d(TAG, "LoginScreen: navigateToCompleteProfile: $email")
            navController.navigate("completeProfile/${email.trim()}")
            Log.d(TAG, "LoginScreen: navigateToCompleteProfile COMPLETED")
        } else if (loginSuccess) {
            loginViewModel.resetLoginState()
            sharedViewModel.setEmail(email.trim())
            Log.d(TAG, "LoginScreen: navigateToHome: $email")
            navController.navigate("home")
            Log.d(TAG, "LoginScreen: navigateToHome COMPLETED")
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
                    loginViewModel.clearErrorMessage()
                },
                onPasswordVisibilityToggle = { loginViewModel.togglePasswordVisibility() }
            )

            AppButton(onClick = {
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
