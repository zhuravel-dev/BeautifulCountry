package com.example.studyprojectrnc.data.dataSource.local.model

import io.realm.RealmObject

open class ModelImageRealm(
    var largeImageURL: String? = null,
    var views : Int? = null
) : RealmObject() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ModelImageRealm

        if (largeImageURL != other.largeImageURL) return false
        if (views != other.views) return false

        return true
    }

    override fun hashCode(): Int {
        var result = largeImageURL?.hashCode() ?: 0
        result = 31 * result + (views ?: 0)
        return result
    }
}