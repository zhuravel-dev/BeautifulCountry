package com.example.studyprojectrnc.ui.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkManager
import com.example.studyprojectrnc.data.repository.LocationRepositoryRoom
import com.example.studyprojectrnc.data.roomForLocation.RoomDB
import com.example.studyprojectrnc.data.roomForLocation.Entity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirstFragmentViewModel : ViewModel() {
    private lateinit var database: RoomDB
    private val repository by lazy {LocationRepositoryRoom(database) }

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
}

