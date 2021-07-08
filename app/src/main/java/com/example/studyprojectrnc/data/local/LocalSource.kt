package com.example.studyprojectrnc.data.local

import android.location.Location
import com.example.studyprojectrnc.data.realmforImage.ModelImageRealm
import com.example.studyprojectrnc.data.realmforImage.ModelLocationRealm
import com.example.studyprojectrnc.repository.model.Hits
import io.realm.Realm
import java.util.concurrent.Executors

class LocalSource {

    private val realm: Realm = run {
        Realm.getDefaultInstance()
    }

    private val execService = Executors.newSingleThreadExecutor()

    private fun getImageRealmObjects(): List<ModelImageRealm> =
        realm.where(ModelImageRealm::class.java).findAll()

    fun saveImageRealmObjects(models: List<Hits>?, callback: (List<ModelImageRealm>) -> Unit) {
        realm.executeTransactionAsync(
            { realm ->
                models?.map {
                    ModelImageRealm(it.id, it.largeImageURL)
                }?.let(realm::copyToRealm)
            },
            { callback.invoke(getImageRealmObjects()) },
            { callback.invoke(getImageRealmObjects()) }
        )
    }

    fun saveLocationRealm(location: Location) {
        execService.submit {
        realm.executeTransactionAsync { realm ->
            ModelLocationRealm(location.latitude, location.longitude, location.altitude)
                .let(realm::copyToRealm)
        }}
    }

    fun getLocationRealm(): List<Location> =
        realm.where(ModelLocationRealm::class.java).findAll().map { it.run {
            Location("Realm").apply {
                latitude = it.latitude
                longitude = it.longitude
                altitude = it.altitude
            }
        } }
}