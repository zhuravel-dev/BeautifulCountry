package com.example.studyprojectrnc.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import com.example.studyprojectrnc.data.db.ModelLocationRealm
import com.example.studyprojectrnc.data.local.LocalSource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import io.realm.Realm
import java.util.concurrent.Executors

class LocationRepository(private val locationClient: FusedLocationProviderClient) {

    private val localSource = LocalSource()
    private val execService = Executors.newSingleThreadExecutor()
    private val realm: Realm = run {
        Realm.getDefaultInstance()
    }

    @SuppressLint("MissingPermission")
    fun getLocation() {
        locationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let(localSource::saveLocationRealm)
                startLocationUpdates()
                location?.also { setLocationData(it) }
                Log.i("LocationRepository", "location saved $location")
            }
        getLocationRealm()
    }

    fun getLocationRealm(): List<Location> =
        realm.where(ModelLocationRealm::class.java).findAll().map {
            it.run {
                Location("Realm").apply {
                    latitude = it.latitude
                    longitude = it.longitude
                    altitude = it.altitude
                }
            }
        }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        locationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return
            for (location in locationResult.locations) {
                setLocationData(location)
            }
        }
    }

    private fun setLocationData(location: Location) {
        execService.submit {
            realm.executeTransactionAsync { realm ->
                ModelLocationRealm(location.latitude, location.longitude, location.altitude)
                    .let(realm::copyToRealm)

            }
        }
    }

    private val locationRequest: LocationRequest = LocationRequest.create().apply {
        interval = 10000
        fastestInterval = 5000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
}
