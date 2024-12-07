package com.bignerdranch.android.composepopuptrip.ui.screens.routeMap

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bignerdranch.android.composepopuptrip.ui.components.LoginTitle

@Composable
fun RouteMap(startAddress: String, destinationAddress: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoginTitle(startAddress)

        Spacer(modifier = Modifier.height(16.dp))

        LoginTitle(destinationAddress)
    }
}