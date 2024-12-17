package com.bignerdranch.android.composepopuptrip.presentation.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bignerdranch.android.composepopuptrip.data.entities.User
import com.bignerdranch.android.composepopuptrip.presentation.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    sharedViewModel: SharedViewModel
) {
    val userState by viewModel.user.collectAsState()
    val email by sharedViewModel.email.collectAsState()

    LaunchedEffect(email) {
        email?.let {
            viewModel.fetchUser(it)
        }
    }

    // UI
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Profile") })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (userState == null) {
                CircularProgressIndicator() // Show loading if user is not fetched yet
            } else {
                ProfileContent(user = userState!!)
            }
        }
    }
}

@Composable
fun ProfileContent(user: User) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Welcome, ${user.username ?: "User"}!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Email: ${user.email}",
            fontSize = 18.sp
        )

        // Add any additional user info here
    }
}