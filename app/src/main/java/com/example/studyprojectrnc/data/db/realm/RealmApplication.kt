package com.example.studyprojectrnc.data.db.realm

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RealmApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        /*Realm.init(applicationContext)
        val realmConfiguration = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)*/
    }
}