package com.example.studyprojectrnc.data.roomForLocation

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {
    @Query("SELECT * FROM location ORDER BY longitude ASC")
    fun getLocationDetails(): LiveData<Entity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertLocation(locationRoom: Entity)

    @Query("DELETE FROM location")
    fun deleteAll()
}