package com.bignerdranch.android.composepopuptrip.presentation.components

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfilePlaceTypeSelector(
    placeTypes: List<String>,
    selectedTypes: List<String>,
    onSelectionChanged: (List<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(

        modifier = modifier
    ) {
        placeTypes.forEach { type ->
            val isSelected = selectedTypes.contains(type)
            FilterChip(
                selected = isSelected,
                onClick = {
                    val updatedTypes = selectedTypes.toMutableList()
                    if (isSelected) {
                        updatedTypes.remove(type)
                    } else {
                        updatedTypes.add(type)
                    }
                    onSelectionChanged(updatedTypes)
                },
                label = { Text(type) }
            )
        }
    }
}