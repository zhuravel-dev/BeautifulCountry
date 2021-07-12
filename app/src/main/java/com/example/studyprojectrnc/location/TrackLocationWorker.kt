package com.example.studyprojectrnc.location

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.studyprojectrnc.data.repository.LocationRepositoryRoom
import com.example.studyprojectrnc.data.roomForLocation.RoomDB

class TrackLocationWorker constructor(
    val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    private lateinit var database: RoomDB
    private val repository by lazy { LocationRepositoryRoom(database) }

    override suspend fun doWork() =
        try{
            repository.fetchLocation()
            Log.i("TAG", "Call fetchLocation() in Worker")
            Result.success()

        } catch (e: Exception) {
            Log.e("TAG", "Catch Exception fetchLocation() in Worker")
            Result.failure()
        }
}
