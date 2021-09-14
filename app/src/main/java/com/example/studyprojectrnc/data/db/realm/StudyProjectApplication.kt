package com.example.studyprojectrnc.data.db.realm

import android.app.Application
import android.content.Context
import com.example.studyprojectrnc.BuildConfig
import com.example.studyprojectrnc.utils.AppPreferences
import com.example.studyprojectrnc.utils.log.DebugTree
import com.example.studyprojectrnc.utils.log.ReleaseTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class StudyProjectApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setup()
        /*Realm.init(applicationContext)
        val realmConfiguration = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)*/
    }

    private fun setup() {
        val sharesPreferences =
            this.getSharedPreferences(BuildConfig.PREF_NAME, Context.MODE_PRIVATE)
        val appPreferences = AppPreferences(sharesPreferences)

        if (BuildConfig.DEBUG) Timber.plant(DebugTree(appPreferences)) else Timber.plant(
            ReleaseTree(
                appPreferences
            )
        )
    }
}