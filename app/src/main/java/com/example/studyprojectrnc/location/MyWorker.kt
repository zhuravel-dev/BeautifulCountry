package com.example.studyprojectrnc.location

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.studyprojectrnc.data.repository.LocationRepositoryRoom
import com.example.studyprojectrnc.data.roomForLocation.RoomDB
import com.example.studyprojectrnc.musicPlayer.BackgroundSoundService

class MyWorker constructor(
    val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

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
}
