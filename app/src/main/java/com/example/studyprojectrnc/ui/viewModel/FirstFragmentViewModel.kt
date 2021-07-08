package com.example.studyprojectrnc.ui.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.studyprojectrnc.TrackLocationWorker
import com.example.studyprojectrnc.data.repository.LocationRepository
import com.example.studyprojectrnc.data.roomForLocation.RoomDB
import com.example.studyprojectrnc.data.roomForLocation.Entity
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class FirstFragmentViewModel : ViewModel() {
    //private var locationClient = FusedLocationProviderClient()
    private lateinit var database: RoomDB
    private val repository by lazy {LocationRepository(database) }

    fun initRepo(context: Context) {
        database = RoomDB.getDatabaseClient(context)
    }

    fun fetchAllLocation() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.fetchLocation(
                )
            }
        }
    }

    fun getLocation(): LiveData<Entity> {
        return repository.getLocationLiveData()
    }

    fun stopTrackLocation() {
        WorkManager.getInstance().cancelAllWorkByTag(LOCATION_WORK_TAG)
    }

    companion object {
        const val LOCATION_WORK_TAG = "LOCATION_WORK_TAG"
    }
}

