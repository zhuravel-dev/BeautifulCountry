package com.example.studyprojectrnc.data.realmForImage

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)
        val realmConfiguration = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }
}