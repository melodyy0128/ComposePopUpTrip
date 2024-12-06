package com.bignerdranch.android.composepopuptrip.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController
import com.bignerdranch.android.composepopuptrip.ui.screens.home.HomeScreen
import com.bignerdranch.android.composepopuptrip.ui.screens.home.HomeViewModel
import com.bignerdranch.android.composepopuptrip.ui.screens.login.LoginScreen
import com.bignerdranch.android.composepopuptrip.ui.screens.login.PasswordResetScreen
import com.bignerdranch.android.composepopuptrip.ui.screens.login.SignUpScreen

@Composable
fun NavigationGraph(homeViewModel: HomeViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        // Login Screen
        composable("login") {
            LoginScreen(navController = navController)
        }

        composable("home") {
            HomeScreen(homeViewModel = homeViewModel)
        }

        composable("signup") {
            SignUpScreen(navController = navController)
        }

        composable("resetPassword"){
            PasswordResetScreen(navController = navController)
        }
    }
}
