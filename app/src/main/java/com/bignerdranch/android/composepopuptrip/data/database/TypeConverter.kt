package com.bignerdranch.android.composepopuptrip.data.database

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromPlaceTypesList(placeTypes: List<String>): String {
        return placeTypes.joinToString(",") // Convert list to comma-separated string
    }

    @TypeConverter
    fun toPlaceTypesList(data: String): List<String> {
        return if (data.isEmpty()) emptyList() else data.split(",") // Convert string back to list
    }
}