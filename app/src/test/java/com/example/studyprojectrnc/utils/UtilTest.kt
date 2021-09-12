package com.example.studyprojectrnc.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.location.LocationManager
import com.example.studyprojectrnc.services.LocationService
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class UtilTest {

    @Test
    fun testIsMyServiceRunningShouldReturnTrue() {
        val locationService = LocationService()
        val componentName: ComponentName = mock {
            on { className } doReturn locationService::class.java.name
        }
        val service: ActivityManager.RunningServiceInfo = mock()
        service.service = componentName
        val runningServices: List<ActivityManager.RunningServiceInfo> = listOf(service)
        val manager: ActivityManager = mock {
            on { getRunningServices(Int.MAX_VALUE) } doReturn runningServices
        }
        val activity: Activity = mock {
            on { getSystemService(Context.ACTIVITY_SERVICE) } doReturn manager
        }
        val clazz = locationService::class.java
        val isRunning = Util.isMyServiceRunning(clazz, activity)
        assertEquals(isRunning, true)
    }

    @Test
    fun testIsMyServiceRunningShouldReturnFalse() {

        val locationService = LocationService()
        val componentName: ComponentName = mock {
            on { className } doReturn Any()::class.java.name
        }
        val service: ActivityManager.RunningServiceInfo = mock()
        service.service = componentName
        val runningServices: List<ActivityManager.RunningServiceInfo> = listOf(service)
        val manager: ActivityManager = mock {
            on { getRunningServices(Int.MAX_VALUE) } doReturn runningServices
        }
        val activity: Activity = mock {
            on { getSystemService(Context.ACTIVITY_SERVICE) } doReturn manager
        }
        val clazz = locationService::class.java
        val isNotRunning = Util.isMyServiceRunning(clazz, activity)
        assertEquals(isNotRunning, false)
    }

    @Test
    fun testIsLocationEnabled() {

        val locationManager: LocationManager = mock {
            on { isProviderEnabled(LocationManager.GPS_PROVIDER) } doReturn true
        }
        val myContext: Context = mock {
            on { getSystemService(Context.LOCATION_SERVICE) } doReturn locationManager
        }
        val enabled = Util.isLocationEnabledOrNot(myContext)
        assertEquals(enabled, true)
    }

    @Test
    fun testLocationIsNotEnabled() {

        val locationManager: LocationManager = mock {
            on { isProviderEnabled(LocationManager.GPS_PROVIDER) } doReturn false
        }
        val myContext: Context = mock {
            on { getSystemService(Context.LOCATION_SERVICE) } doReturn locationManager
        }
        val enabled = Util.isLocationEnabledOrNot(myContext)
        assertEquals(enabled, false)
        }

}