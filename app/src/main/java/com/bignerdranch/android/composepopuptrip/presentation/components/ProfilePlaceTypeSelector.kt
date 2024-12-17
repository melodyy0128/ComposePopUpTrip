package com.bignerdranch.android.composepopuptrip.presentation.components

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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
                label = {
                    Text(
                        text = type,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}