package com.example.studyprojectrnc.location

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*

const val NOTIFICATION_CHANNEL_ID = "get location background"
const val FIFTEEN_MINUTES: Long = 1000
const val RESTART_SERVICE = "restart service"
const val LOCATION_UPDATE = "location update"

class LocationService: Service() {
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var altitude: Double = 0.0

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) createNotificationChanel() else startForeground(
            1,
            Notification()
        )
        requestLocationUpdates()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChanel() {
        val channelName = "Background Service"
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            channelName,
            NotificationManager.IMPORTANCE_NONE
        )
        channel.lightColor = Color.BLUE
        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val manager =
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        manager.createNotificationChannel(channel)
        val notificationBuilder =
            NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        val notification: Notification = notificationBuilder.setOngoing(true)
            .setPriority(NotificationManager.IMPORTANCE_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()
        startForeground(2, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        return START_STICKY
    }

    private fun requestLocationUpdates() {
        val request = LocationRequest()
        request.interval = FIFTEEN_MINUTES
        request.fastestInterval = 1000
        request.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        val client: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)

        val permission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (permission == PackageManager.PERMISSION_GRANTED) {
            client.requestLocationUpdates(request, object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    longitude = locationResult.lastLocation.longitude
                    latitude = locationResult.lastLocation.latitude
                    altitude = locationResult.lastLocation.altitude
                    Log.d(LOCATION_SERVICE, LOCATION_UPDATE)
                    if (latitude != 0.0 && longitude != 0.0) {
                        Log.d(
                            "Location::",
                            latitude.toString() + ":::" + longitude.toString()
                        )
                    }
                }
            }, null)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val broadcastIntent: Intent = Intent().apply {
            action = RESTART_SERVICE
            setClass(this@LocationService, RestartBackgroundService::class.java)
        }
        this.sendBroadcast(broadcastIntent)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}