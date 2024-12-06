package com.bignerdranch.android.composepopuptrip
import android.app.Application
import com.google.android.libraries.places.api.Places

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Google Places API
        Places.initialize(applicationContext, "AIzaSyBH1OWT7-laL_krqGsYWg17-GwKupFhgec")
    }
}