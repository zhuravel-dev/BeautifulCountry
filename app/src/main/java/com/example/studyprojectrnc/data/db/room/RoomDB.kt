package com.example.studyprojectrnc.data.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entity::class], version = 1)
abstract class RoomDB : RoomDatabase(){
    abstract fun locationDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getDatabaseClient(context: Context): RoomDB {

            if (INSTANCE != null) return INSTANCE as RoomDB

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, RoomDB::class.java, "LOCATION_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE as RoomDB
            }
        }
    }
}
