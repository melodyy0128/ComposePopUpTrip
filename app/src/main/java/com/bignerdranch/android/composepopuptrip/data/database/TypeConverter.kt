package com.bignerdranch.android.composepopuptrip.data.database

import androidx.room.TypeConverter
import com.bignerdranch.android.composepopuptrip.data.entities.PlaceType

class Converters {
    @TypeConverter
    fun fromPlaceTypeList(placeTypes: List<PlaceType>?): String? {
        return placeTypes?.joinToString(",") { it.name }
    }

    @TypeConverter
    fun toPlaceTypeList(data: String?): List<PlaceType>? {
        return data?.split(",")?.map { PlaceType.valueOf(it) }
    }
}