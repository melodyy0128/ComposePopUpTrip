package com.bignerdranch.android.composepopuptrip.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController
import com.bignerdranch.android.composepopuptrip.ui.screens.home.HomeScreen
import com.bignerdranch.android.composepopuptrip.ui.screens.login.LoginScreen

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        // Login Screen
        composable("login") {
            LoginScreen(navController = navController)
        }

        // Home Screen with email passed as an argument
        composable(
            route = "home/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: "Unknown"
            HomeScreen(userEmail = email)
        }
    }
}
