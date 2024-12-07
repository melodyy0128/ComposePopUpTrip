package com.bignerdranch.android.composepopuptrip.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
        painter = painterResource(id = R.drawable.pop_up_trip_icon),
        contentDescription = "App Logo",
        modifier = Modifier
            .size(150.dp)
            .padding(bottom = 16.dp)
    )
}

@Composable
fun LoginTitle(titleText: String = "Sign In") {
    Text(
        text = titleText,
        fontSize = 24.sp,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
fun TextInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(onClick = { onValueChange("") }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear Input"
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
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
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun LoginButton(onClick: () -> Unit, buttonText: String = "Login") {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(48.dp)
    ) {
        Text(text = buttonText, fontSize = 16.sp)
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

@Composable
fun PopupDialog(onDismiss: () -> Unit, text: String, buttonText: String) {
    androidx.compose.ui.window.Dialog(onDismissRequest = onDismiss) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.medium
                )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Action Button
                Button(
                    onClick = { onDismiss() },
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth(0.5f)
                ) {
                    Text(
                        text = buttonText,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

