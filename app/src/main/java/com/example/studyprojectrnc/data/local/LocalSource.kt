package com.example.studyprojectrnc.data.local

import com.example.studyprojectrnc.data.realmForImage.ModelImageRealm
import io.realm.Realm

class LocalSource {

    private val realm: Realm = run {
        Realm.getDefaultInstance()
    }

    private fun getImageRealmObjects(): List<ModelImageRealm> =
        realm.where(ModelImageRealm::class.java).findAll()

// TODO: 06.08.2021  this method saved data to the database,
//  now this is done in the class ImagesRepositoryRealm.
//  The method is saved for the future.
  /*  fun saveImageRealmObjects(models: List<Response>?, callback: (List<ModelImageRealm>) -> Unit) {
        realm.executeTransactionAsync(
            { realm ->
                models?.map {
                    ModelImageRealm(it.id, it.largeImageURL)
                }?.let(realm::copyToRealm)
            },
            { callback.invoke(getImageRealmObjects()) },
            { callback.invoke(getImageRealmObjects()) }
        )
    }*/
}