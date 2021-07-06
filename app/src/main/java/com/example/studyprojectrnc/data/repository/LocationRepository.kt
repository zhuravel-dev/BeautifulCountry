package com.example.studyprojectrnc.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import com.example.studyprojectrnc.data.local.LocalSource
import com.google.android.gms.location.FusedLocationProviderClient

class LocationRepository(private val locationClient: FusedLocationProviderClient) {

    private val localSource = LocalSource()

    @SuppressLint("MissingPermission")
    fun getLocation() {
        locationClient.lastLocation
                ?.addOnSuccessListener { location: Location? ->
                    location?.let(localSource::saveLocationRealm)
                    Log.e("LocationRepository", "location saved $location")
                }
    }
}