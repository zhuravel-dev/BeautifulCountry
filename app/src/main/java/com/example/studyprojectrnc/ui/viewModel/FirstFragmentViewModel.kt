package com.example.studyprojectrnc.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.studyprojectrnc.TrackLocationWorker
import java.util.concurrent.TimeUnit

class FirstFragmentViewModel : ViewModel() {

    fun trackLocation() {
        val locationWorker =
            PeriodicWorkRequestBuilder<TrackLocationWorker>(
                15, TimeUnit.SECONDS)
                .addTag(LOCATION_WORK_TAG)
                .build()
        WorkManager
            .getInstance()
            .enqueueUniquePeriodicWork(
                LOCATION_WORK_TAG,
                ExistingPeriodicWorkPolicy.KEEP,
                locationWorker
            )
    }

    fun stopTrackLocation() {
        WorkManager.getInstance().cancelAllWorkByTag(LOCATION_WORK_TAG)
    }

    companion object {
        const val LOCATION_WORK_TAG = "LOCATION_WORK_TAG"
    }
}