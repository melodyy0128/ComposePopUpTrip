package com.bignerdranch.android.composepopuptrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.bignerdranch.android.composepopuptrip.data.database.DatabaseProvider
import com.bignerdranch.android.composepopuptrip.data.database.UserDatabase
import com.bignerdranch.android.composepopuptrip.data.repository.AuthRepository
import com.bignerdranch.android.composepopuptrip.data.repository.GooglePlacesRepository
import com.bignerdranch.android.composepopuptrip.data.repository.UserRepository
import com.bignerdranch.android.composepopuptrip.presentation.NavigationGraph
import com.bignerdranch.android.composepopuptrip.presentation.SharedViewModel
import com.bignerdranch.android.composepopuptrip.presentation.screens.home.HomeViewModel
import com.bignerdranch.android.composepopuptrip.presentation.screens.login.LoginViewModel
import com.bignerdranch.android.composepopuptrip.presentation.screens.profile.CompleteProfileViewModel
import com.bignerdranch.android.composepopuptrip.presentation.screens.profile.ProfileViewModel
import com.bignerdranch.android.composepopuptrip.presentation.theme.ComposePopUpTripTheme
import com.google.android.libraries.places.api.Places

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val placesClient = Places.createClient(this)

        // Create Repository and ViewModel
        val placesRepository = GooglePlacesRepository(placesClient)

        val db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java, "user_database"
        )
            // clean database on schema change
            .fallbackToDestructiveMigration()
            .build()

        val userRepository = UserRepository(db.userDao())
        val authRepository = AuthRepository()

        val sharedViewModel: SharedViewModel by viewModels()
        val loginViewModel = LoginViewModel(userRepository, authRepository)
        val homeViewModel = HomeViewModel(placesRepository)
        val completeProfileViewModel = CompleteProfileViewModel(userRepository)
        val profileViewModel = ProfileViewModel(userRepository)

        setContent {
            ComposePopUpTripTheme {
                NavigationGraph(
                    sharedViewModel = sharedViewModel,
                    loginViewModel = loginViewModel,
                    homeViewModel = homeViewModel,
                    completeProfileViewModel = completeProfileViewModel,
                    profileViewModel = profileViewModel
                )
            }
        }
    }
}
