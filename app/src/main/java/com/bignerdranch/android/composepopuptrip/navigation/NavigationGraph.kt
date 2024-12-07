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
import com.bignerdranch.android.composepopuptrip.ui.screens.routeMap.RouteMap

@Composable
fun NavigationGraph(homeViewModel: HomeViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        // Login Screen
        composable("login") {
            LoginScreen(navController = navController)
        }

        composable("signup") {
            SignUpScreen(navController = navController)
        }

        composable("resetPassword"){
            PasswordResetScreen(navController = navController)
        }

        composable("home") {
            HomeScreen(navController = navController, homeViewModel = homeViewModel)
        }

        composable(
            route = "routeMap/{startAddress}/{destinationAddress}",
            arguments = listOf(
                navArgument("startAddress") { type = NavType.StringType },
                navArgument("destinationAddress") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val startAddress = backStackEntry.arguments?.getString("startAddress") ?: ""
            val destinationAddress = backStackEntry.arguments?.getString("destinationAddress") ?: ""
            RouteMap(startAddress = startAddress, destinationAddress = destinationAddress)
        }


    }
}
