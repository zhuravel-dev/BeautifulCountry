package com.example.studyprojectrnc.db

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import io.realm.OrderedRealmCollectionChangeListener
import io.realm.RealmModel
import io.realm.RealmResults

class LiveRealmResults {
    class LiveRealmResults<T : RealmModel?> @MainThread constructor(results: RealmResults<T>) :
        LiveData<List<T>?>() {
        private val results: RealmResults<T>

        private val listener =
            OrderedRealmCollectionChangeListener<RealmResults<T>> { results, changeSet ->
                this@LiveRealmResults.setValue(
                    results
                )
            }

        override fun onActive() {
            super.onActive()
            if (results.isValid) {
                results.addChangeListener(listener)
            }
        }

        override fun onInactive() {
            super.onInactive()
            if (results.isValid) {
                results.removeChangeListener(listener)
            }
        }

        init {
            require(results.isManaged) { "LiveRealmResults only supports managed RealmModel instances" }
            require(results.isValid) { "Realm instance that owns it is closed. It can no longer be observed for changes" }
            this.results = results
            if (results.isLoaded) {
                setValue(results)
            }
        }
    }
}