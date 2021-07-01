package com.example.studyprojectrnc.db

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.RealmObjectChangeListener

class LiveRealmObject<T : RealmModel?> @MainThread constructor(obj: T?) : MutableLiveData<T>() {

    private val listener =
        RealmObjectChangeListener<T> { obj, objectChangeSet ->
            if (!objectChangeSet!!.isDeleted) {
                setValue(obj)
            } else {
                setValue(null)
            }
        }

    override fun onActive() {
        super.onActive()
        val obj = value
        if (obj != null && RealmObject.isValid(obj)) {
            RealmObject.addChangeListener(obj, listener)
        }
    }

    override fun onInactive() {
        super.onInactive()
        val obj = value
        if (obj != null && RealmObject.isValid(obj)) {
            RealmObject.removeChangeListener(obj, listener)
        }
    }

    var value : T? = obj
}