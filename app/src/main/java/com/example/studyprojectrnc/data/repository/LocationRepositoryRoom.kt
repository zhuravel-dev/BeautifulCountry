package com.example.studyprojectrnc.data.repository

import androidx.lifecycle.LiveData
import com.example.studyprojectrnc.data.roomForLocation.RoomDB
import com.example.studyprojectrnc.data.roomForLocation.Entity
import com.example.studyprojectrnc.location.LocationService


class LocationRepositoryRoom(val database: RoomDB) {

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

    fun getLocationLiveData(): LiveData<Entity> = database.locationDao().getLocationDetails()
}

