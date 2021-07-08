package com.example.studyprojectrnc

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.studyprojectrnc.data.repository.LocationRepository
import com.example.studyprojectrnc.data.roomForLocation.RoomDB
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class TrackLocationWorker constructor(
    val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    private lateinit var locationClient: FusedLocationProviderClient
    private lateinit var database: RoomDB
    private val repository by lazy { LocationRepository(database) }
    private val locationService = LocationService()

    /*   private val repository = LocationRepository(
           LocationServices.getFusedLocationProviderClient(context)
       )
   */
    override suspend fun doWork() =
        try{
            repository.fetchLocation()
            Log.i("TAG", "Call fetchLocation() in Worker")
            Result.success()

        } catch (e: Exception) {
            Result.failure()
        }
}
