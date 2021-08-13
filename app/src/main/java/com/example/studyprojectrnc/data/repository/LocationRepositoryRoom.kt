package com.example.studyprojectrnc.data.repository

import com.example.studyprojectrnc.data.db.room.Entity
import com.example.studyprojectrnc.data.db.room.RoomDB
import com.example.studyprojectrnc.services.LocationService
import javax.inject.Inject

class LocationRepositoryRoom @Inject constructor(private val database: RoomDB){

    /*lateinit var database: RoomDB

    init {
        initRepo(context)
    }
    private fun initRepo(context: Context) {
        database = RoomDB.getDatabaseClient(context)
    }*/
    /*
    @Inject
    lateinit var database: RoomDB*/

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

