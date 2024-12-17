package com.bignerdranch.android.composepopuptrip.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
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
import com.bignerdranch.android.composepopuptrip.presentation.components.BottomNavItem
import com.bignerdranch.android.composepopuptrip.presentation.components.BottomNavigationBar
import com.bignerdranch.android.composepopuptrip.presentation.screens.home.HomeScreen
import com.bignerdranch.android.composepopuptrip.presentation.screens.home.HomeViewModel
import com.bignerdranch.android.composepopuptrip.presentation.screens.login.LoginScreen
import com.bignerdranch.android.composepopuptrip.presentation.screens.login.PasswordResetScreen
import com.bignerdranch.android.composepopuptrip.presentation.screens.login.SignUpScreen
import com.bignerdranch.android.composepopuptrip.presentation.screens.home.RouteMap
import com.bignerdranch.android.composepopuptrip.presentation.screens.login.LoginViewModel
import com.bignerdranch.android.composepopuptrip.presentation.screens.profile.CompleteProfileScreen
import com.bignerdranch.android.composepopuptrip.presentation.screens.profile.CompleteProfileViewModel
import com.bignerdranch.android.composepopuptrip.presentation.screens.profile.ProfileScreen
import com.bignerdranch.android.composepopuptrip.presentation.screens.profile.ProfileViewModel
import com.bignerdranch.android.composepopuptrip.presentation.screens.saved.SavedPlacesScreen
import com.bignerdranch.android.composepopuptrip.presentation.screens.saved.SavedRoutesScreen
import com.bignerdranch.android.composepopuptrip.presentation.screens.saved.SavedScreen
import com.bignerdranch.android.composepopuptrip.presentation.screens.profile.SettingsScreen

//private const val TAG = ""

fun shouldShowBottomNav(currentRoute: String?): Boolean {
    return currentRoute in listOf(
        "home",
        "saved",
        "profile",
        "settings",
        "savedPlaces",
        "savedRoutes"
    ) || currentRoute?.startsWith("routeMap/") == true
}

@Composable
fun NavigationGraph(
    sharedViewModel: SharedViewModel,
    loginViewModel: LoginViewModel,
    homeViewModel: HomeViewModel,
    completeProfileViewModel: CompleteProfileViewModel,
    profileViewModel: ProfileViewModel
) {
    val navController = rememberNavController()

    val bottomNavItems = listOf(
        BottomNavItem("Home", "home", Icons.Filled.Home),
        BottomNavItem("Saved", "saved", Icons.Filled.Star),
        BottomNavItem("Profile", "profile", Icons.Filled.AccountCircle)
    )

    Scaffold(
        bottomBar = {
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = currentBackStackEntry?.destination?.route
//            Log.d("NavDebug", "Current Route: $currentRoute")
            if (shouldShowBottomNav(currentRoute)) {
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
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {

            composable("login") {
                LoginScreen(
                    navController = navController,
                    loginViewModel = loginViewModel,
                    sharedViewModel = sharedViewModel
                )
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
                SavedScreen(navController = navController)
            }

            composable(
                route = "completeProfile/{email}",
                arguments = listOf(
                    navArgument("email") { type = NavType.StringType })
            ) { backStackEntry ->
                val email = backStackEntry.arguments?.getString("email") ?: ""
                CompleteProfileScreen(email, navController, completeProfileViewModel)
            }

            composable("profile") {
                ProfileScreen(
                    viewModel = profileViewModel,
                    sharedViewModel = sharedViewModel
                )
            }

            composable("settings") {
                SettingsScreen(

                )
            }

            composable("savedPlaces") {
                SavedPlacesScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable("savedRoutes") {
                SavedRoutesScreen()
            }
        }
    }

}