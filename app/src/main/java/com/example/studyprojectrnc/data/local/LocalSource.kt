package com.example.studyprojectrnc.data.local

import android.location.Location
import com.example.studyprojectrnc.data.realmForImage.ModelImageRealm
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
}