//package com.bignerdranch.android.composepopuptrip.presentation.screens.profile
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.foundation.layout.Column
//import androidx.compose.material3.Text
//
//@Composable
//fun ProfileScreen(
//    email: String,
//    viewModel: ProfileViewModel
//) {
//    val user = viewModel.fetchUser(email)
//
//    Column {
//        user?.let {
//            Text("Email: ${it.email}")
//            Text("Username: ${it.username ?: "Not set"}")
//        } ?: Text("Loading...")
//    }
//}