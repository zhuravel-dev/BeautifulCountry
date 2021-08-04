package com.example.studyprojectrnc.data.local

import com.example.studyprojectrnc.data.realmForImage.ModelImageRealm
import com.example.studyprojectrnc.data.retrofit.model.Response
import io.realm.Realm

class LocalSource {

    private val realm: Realm = run {
        Realm.getDefaultInstance()
    }

    private fun getImageRealmObjects(): List<ModelImageRealm> =
        realm.where(ModelImageRealm::class.java).findAll()

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