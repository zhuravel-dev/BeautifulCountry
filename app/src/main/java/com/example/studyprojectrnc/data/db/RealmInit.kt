package com.example.studyprojectrnc.data.db

import android.app.Application
import io.realm.Realm

class RealmInit: Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)
    }
}