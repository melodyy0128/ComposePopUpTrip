package com.bignerdranch.android.composepopuptrip
import android.app.Application
import com.bignerdranch.android.composepopuptrip.BuildConfig
import com.google.android.libraries.places.api.Places

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        val apiKey = BuildConfig.GOOGLE_API_KEY

        // Initialize Google Places API
        Places.initialize(applicationContext, apiKey)
    }
}