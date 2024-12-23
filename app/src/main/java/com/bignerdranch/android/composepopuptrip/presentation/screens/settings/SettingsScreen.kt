package com.bignerdranch.android.composepopuptrip.presentation.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bignerdranch.android.composepopuptrip.presentation.components.SettingsClickableItem
import com.bignerdranch.android.composepopuptrip.presentation.components.SettingsDropdown
import com.bignerdranch.android.composepopuptrip.presentation.components.SettingsSlider
import com.bignerdranch.android.composepopuptrip.presentation.components.SettingsToggle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Settings") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp), // Consistent horizontal padding
            verticalArrangement = Arrangement.spacedBy(24.dp) // Even spacing between sections
        ) {

            // Push Notifications
            SettingsToggle(
                title = "Push Notifications",
                isChecked = true,
                onCheckedChange = { }
            )

            // Dark Mode
            SettingsToggle(
                title = "Dark Mode",
                isChecked = true,
                onCheckedChange = { }
            )

            // Privacy Policy
            SettingsClickableItem(
                title = "Privacy Policy",
                onClick = {}
            )

            // Language Selector
            SettingsDropdown(
                title = "Language",
                selectedItem = "English",
                items = listOf("English", "Spanish", "French", "German", "Chinese"),
                onItemSelected = { }
            )

            // Search Range
            SettingsSlider(
                title = "Search Range",
                value = 10f,
                valueRange = 1f..50f,
                onValueChange = { }
            )

            Spacer(modifier = Modifier.weight(1f))

            // Log Out Button
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Log Out")
            }
        }
    }
}