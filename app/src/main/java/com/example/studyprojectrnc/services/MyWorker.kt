/*
package com.example.studyprojectrnc.services

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.studyprojectrnc.data.repository.LocationRepositoryRoom
import javax.inject.Inject

TODO fix this with Assisted Inject
 https://proandroiddev.com/dagger-2-setup-with-workmanager-a-complete-step-by-step-guild-bb9f474bde37

class MyWorker constructor(
    val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    @Inject

    private val repository by lazy { LocationRepositoryRoom(context) }

    override suspend fun doWork(): Result {

        context.startService(Intent(context, BackgroundSoundService::class.java))

        return try {
            repository.fetchLocation()
            Log.i("TAG", "Call fetchLocation() in Worker")
            Result.success()

        } catch (e: Exception) {
            Log.e("TAG", "Catch Exception fetchLocation() in Worker ${e.message}")
            Result.failure()
        }
    }
}*/
