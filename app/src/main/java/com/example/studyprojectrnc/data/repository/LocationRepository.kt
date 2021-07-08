package com.example.studyprojectrnc.data.repository

import androidx.lifecycle.LiveData
import com.example.studyprojectrnc.LocationService
import com.example.studyprojectrnc.data.local.LocalSource
import com.example.studyprojectrnc.data.roomForLocation.RoomDB
import com.example.studyprojectrnc.data.roomForLocation.Entity
import com.google.android.gms.location.FusedLocationProviderClient
import java.util.concurrent.Executors

class LocationRepository(val database: RoomDB) {

    private val localSource = LocalSource()
    private val execService = Executors.newSingleThreadExecutor()
    private var myLocationService: LocationService = LocationService()
    /*  private val realm: Realm = run {
        Realm.getDefaultInstance()
    }
*/
    /*   @SuppressLint("MissingPermission")
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
  */

    fun fetchLocation() {
        database.locationDao()
            .insertLocation(
                Entity(
                    latitude = myLocationService.latitude,
                    longitude = myLocationService.longitude,
                    altitude = myLocationService.altitude
                )
            )
    }

    fun getLocationLiveData(): LiveData<Entity> = database.locationDao().getLocationDetails()
}

/*  fun getLocationRealm(): List<Location> =
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
*/