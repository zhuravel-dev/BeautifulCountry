package com.example.studyprojectrnc.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.studyprojectrnc.data.roomForLocation.RoomDB
import com.example.studyprojectrnc.data.roomForLocation.Entity
import com.example.studyprojectrnc.location.LocationService

class LocationRepositoryRoom(context: Context) {

    lateinit var database: RoomDB

    init {
        initRepo(context)
    }
    private fun initRepo(context: Context) {
        database = RoomDB.getDatabaseClient(context)
    }
    private var myLocationService: LocationService = LocationService()

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
}

