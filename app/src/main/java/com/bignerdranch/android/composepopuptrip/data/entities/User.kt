package com.bignerdranch.android.composepopuptrip.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val email: String,
    @ColumnInfo val username: String?,
    @ColumnInfo val placeTypes: List<PlaceType>
)