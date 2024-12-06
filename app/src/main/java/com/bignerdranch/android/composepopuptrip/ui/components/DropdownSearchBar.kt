package com.bignerdranch.android.composepopuptrip.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DropdownSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    suggestions: List<String>,
    onSuggestionClick: (String) -> Unit
) {
    // State to manage dropdown visibility
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Search Bar (TextField)
        OutlinedTextField(
            value = query,
            onValueChange = {
                onQueryChange(it)
                isDropdownExpanded = it.isNotEmpty() && suggestions.isNotEmpty() // Show dropdown if there are suggestions
            },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth()
        )

        // Dropdown Menu
        DropdownMenu(
            expanded = isDropdownExpanded,
            onDismissRequest = { isDropdownExpanded = false }
        ) {
            suggestions.forEach { suggestion ->
                DropdownMenuItem(
                    text = { Text(text = suggestion) },
                    onClick = {
                        onSuggestionClick(suggestion) // Handle suggestion click
                        isDropdownExpanded = false  // Close dropdown after selection
                    }
                )
            }
        }
    }
}
