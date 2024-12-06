package com.bignerdranch.android.composepopuptrip.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bignerdranch.android.composepopuptrip.R


@Composable
fun LoginImage() {
    Image(
        painter = painterResource(id = R.drawable.pop_up_trip_icon), // Replace with your drawable resource
        contentDescription = "App Logo",
        modifier = Modifier
            .size(150.dp) // Adjust size as needed
            .padding(bottom = 16.dp)
    )
}

@Composable
fun LoginTitle() {
    Text(
        text = "Sign In",
        fontSize = 24.sp,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
fun EmailInput(email: String, onEmailChange: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text("Email Address") },
        trailingIcon = {
            if (email.isNotEmpty()) {
                IconButton(onClick = { onEmailChange("") }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Clear Email"
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 60.dp, vertical = 8.dp)
    )
}

@Composable
fun PasswordInput(
    password: String,
    passwordVisibility: Boolean,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityToggle: () -> Unit
) {
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Password") },
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = onPasswordVisibilityToggle) {
                Icon(
                    imageVector = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = "Toggle Password Visibility"
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 60.dp, vertical = 8.dp)
    )
}

@Composable
fun LoginButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 60.dp, vertical = 16.dp)
            .height(48.dp)
    ) {
        Text(text = "Login", fontSize = 16.sp)
    }
}

@Composable
fun AccountActions(onCreateAccount: () -> Unit, onResetPassword: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TextButton(onClick = onCreateAccount) {
            Text("Create An Account")
        }
        TextButton(onClick = onResetPassword) {
            Text("Reset Password")
        }
    }
}

