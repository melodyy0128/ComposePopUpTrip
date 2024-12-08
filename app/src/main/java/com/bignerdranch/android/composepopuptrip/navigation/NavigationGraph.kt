package com.bignerdranch.android.composepopuptrip.navigation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController
import com.bignerdranch.android.composepopuptrip.ui.components.BottomNavItem
import com.bignerdranch.android.composepopuptrip.ui.components.BottomNavigationBar
import com.bignerdranch.android.composepopuptrip.ui.screens.home.HomeScreen
import com.bignerdranch.android.composepopuptrip.ui.screens.home.HomeViewModel
import com.bignerdranch.android.composepopuptrip.ui.screens.login.LoginScreen
import com.bignerdranch.android.composepopuptrip.ui.screens.login.PasswordResetScreen
import com.bignerdranch.android.composepopuptrip.ui.screens.login.SignUpScreen
import com.bignerdranch.android.composepopuptrip.ui.screens.home.RouteMap
import com.bignerdranch.android.composepopuptrip.ui.screens.saved.SavedScreen
import com.bignerdranch.android.composepopuptrip.ui.screens.settings.SettingsScreen

@Composable
fun NavigationGraph(homeViewModel: HomeViewModel) {
    val navController = rememberNavController()

    val bottomNavItems = listOf(
        BottomNavItem("Home", "home", Icons.Filled.Home),
        BottomNavItem("Saved", "saved", Icons.Filled.Star),
        BottomNavItem("Settings", "settings", Icons.Filled.Settings)
    )

    Scaffold(
        bottomBar = {
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = currentBackStackEntry?.destination?.route
            Log.d("NavDebug", "Current Route: $currentRoute")
            if (currentRoute in listOf("home", "saved", "settings")) {
                BottomNavigationBar(
                    items = bottomNavItems,
                    navController = navController,
                    onItemClick = {
                        navController.navigate(it.route)
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {

            composable("login") {
                LoginScreen(navController = navController)
            }

            composable("signup") {
                SignUpScreen(navController = navController)
            }

            composable("resetPassword") {
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
                val destinationAddress =
                    backStackEntry.arguments?.getString("destinationAddress") ?: ""
                RouteMap(startAddress = startAddress, destinationAddress = destinationAddress)
            }

            composable("saved") {
                SavedScreen()
            }

            composable("settings") {
                SettingsScreen()
            }
        }
    }

//    NavHost(navController = navController, startDestination = "home") {
//
//        composable("login") {
//            LoginScreen(navController = navController)
//        }
//
//        composable("signup") {
//            SignUpScreen(navController = navController)
//        }
//
//        composable("resetPassword"){
//            PasswordResetScreen(navController = navController)
//        }
//
//        composable("home") {
//            HomeScreen(navController = navController, homeViewModel = homeViewModel)
//        }
//
//        composable(
//            route = "routeMap/{startAddress}/{destinationAddress}",
//            arguments = listOf(
//                navArgument("startAddress") { type = NavType.StringType },
//                navArgument("destinationAddress") { type = NavType.StringType }
//            )
//        ) { backStackEntry ->
//            val startAddress = backStackEntry.arguments?.getString("startAddress") ?: ""
//            val destinationAddress = backStackEntry.arguments?.getString("destinationAddress") ?: ""
//            RouteMap(startAddress = startAddress, destinationAddress = destinationAddress)
//        }
//    }
}
