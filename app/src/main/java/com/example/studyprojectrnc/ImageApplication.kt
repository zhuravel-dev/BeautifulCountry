package com.example.studyprojectrnc

import android.app.Application
import io.realm.Realm

class ImageApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)
    }
}