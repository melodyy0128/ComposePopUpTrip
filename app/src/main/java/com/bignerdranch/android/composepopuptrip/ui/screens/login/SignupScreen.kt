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
import com.bignerdranch.android.composepopuptrip.ui.components.AppButton
import com.bignerdranch.android.composepopuptrip.ui.components.LoginTitle
import com.bignerdranch.android.composepopuptrip.ui.components.PasswordInput
import com.bignerdranch.android.composepopuptrip.ui.components.PopupDialog
import com.bignerdranch.android.composepopuptrip.ui.components.TextInput

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    val mockNavController = rememberNavController()
    val mockViewModel = SignUpViewModel()

    mockViewModel.updateUserName("MockUser")
    mockViewModel.updateEmail("mock@example.com")

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
                navController.navigate("login")
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

            LoginTitle("Sign Up")

            TextInput(value = userName, onValueChange = { viewModel.updateUserName(it) }, label = "Enter User Name")

            TextInput(value = email, onValueChange = { viewModel.updateEmail(it) }, label = "Enter Email")

            PasswordInput(
                password = password,
                passwordVisibility = passwordVisibility,
                onPasswordChange = { viewModel.updatePassword(it) },
                onPasswordVisibilityToggle = { viewModel.togglePasswordVisibility() }
            )

            PasswordInput(
                password = confirmPassword,
                passwordVisibility = passwordVisibility,
                onPasswordChange = { viewModel.updateConfirmPassword(it) },
                onPasswordVisibilityToggle = { viewModel.togglePasswordVisibility() }
            )

            AppButton(
                onClick = { viewModel.performSignUp() },
                buttonText = "Sign Up"
            )

            TextButton(onClick = { viewModel.navigateToLoginScreen() }) {
                Text(text = "Already have an account? Login")
            }

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
