package com.bignerdranch.android.composepopuptrip.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DropdownSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    suggestions: List<String>,
    onSuggestionClick: (String) -> Unit,
    description: String,
    navController: NavController? = null
) {

    var isDropdownExpanded by remember { mutableStateOf(false) }
    val textFieldWidth = remember { mutableIntStateOf(0) }
    val focusRequester = remember { FocusRequester() }

    Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                onQueryChange(it)
                isDropdownExpanded = it.isNotEmpty() && suggestions.isNotEmpty()
            },
            label = { Text(description) },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(onClick = {
                        onQueryChange("") }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Clear input")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
                .focusRequester(focusRequester)
                .onGloballyPositioned { coordinates ->
                    textFieldWidth.intValue = coordinates.size.width
                }
        )

        DropdownMenu(
            expanded = isDropdownExpanded,
            onDismissRequest = { isDropdownExpanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldWidth.intValue.toDp() })
        ) {
            suggestions.forEach { suggestion ->
                DropdownMenuItem(
                    text = { Text(text = suggestion) },
                    onClick = {
                        onQueryChange(suggestion)
                        onSuggestionClick(suggestion)
                        isDropdownExpanded = false

                        navController?.navigate("home") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ActivateBtn(
    onClick: () -> Unit,
    buttonText: String = "Next",
    enabled: Boolean = true
) {
    Button(
        onClick = { if (enabled) onClick() },
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 64.dp, vertical = 16.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) MaterialTheme.colorScheme.primary else Color.Gray
        )
    ) {
        Text(text = buttonText, fontSize = 16.sp)
    }
}
