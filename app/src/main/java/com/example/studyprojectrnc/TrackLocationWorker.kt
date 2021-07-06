package com.example.studyprojectrnc

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.studyprojectrnc.data.repository.LocationRepository
import com.google.android.gms.location.LocationServices

class TrackLocationWorker constructor(
    val context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    private val repository = LocationRepository(
        LocationServices.getFusedLocationProviderClient(context)
    )

    override fun doWork() =
        try {
            if (context.isGPSEnabled() && context.checkLocationPermission()) {
                repository.getLocation()
                Result.success()
            } else Result.retry()

        } catch (e: Exception) {
            Result.failure()
        }

    private fun Context.isGPSEnabled() =
        (getSystemService(Context.LOCATION_SERVICE) as? LocationManager)
            ?.isProviderEnabled(LocationManager.GPS_PROVIDER) ?: false

    private fun Context.checkLocationPermission(): Boolean =
        checkCallingOrSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED
}
