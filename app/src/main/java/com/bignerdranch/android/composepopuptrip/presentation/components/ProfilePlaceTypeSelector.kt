package com.bignerdranch.android.composepopuptrip.presentation.components

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bignerdranch.android.composepopuptrip.data.entities.PlaceType

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfilePlaceTypeSelector(
    placeTypes: List<PlaceType>,
    selectedTypes: List<PlaceType>,
    onSelectionChanged: (List<PlaceType>) -> Unit,
    isEditing: Boolean = true,
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
                    if (isEditing) {
                        val updatedTypes = selectedTypes.toMutableList()
                        if (isSelected) {
                            updatedTypes.remove(type)
                        } else {
                            updatedTypes.add(type)
                        }
                        onSelectionChanged(updatedTypes)
                    }
                },
                label = {
                    Text(
                        text = type.displayName,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    selectedContainerColor = if (isEditing) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                    labelColor = MaterialTheme.colorScheme.onSurface,
                    selectedLabelColor = if (isEditing) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}
