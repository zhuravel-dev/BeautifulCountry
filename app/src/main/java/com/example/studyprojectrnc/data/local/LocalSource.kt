package com.example.studyprojectrnc.data.local

import com.example.studyprojectrnc.db.ModelRealm
import com.example.studyprojectrnc.repository.model.Hits
import io.realm.Realm

class LocalSource {

    private val realm: Realm = run {
        Realm.getDefaultInstance()
    }

    fun getRealmObjects(): List<ModelRealm> =
        realm.where(ModelRealm::class.java).findAll()

    fun saveData(models: List<Hits>?, callback: (List<ModelRealm>) -> Unit) {
        realm.executeTransactionAsync(
            { realm ->
                models?.map {
                    ModelRealm(it.id, it.largeImageURL)
                }?.let(realm::copyToRealm)
            },
            { callback.invoke(getRealmObjects()) },
            { callback.invoke(getRealmObjects()) }
        )
    }

    fun onCleared() {
        realm.close()
    }
}