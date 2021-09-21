package com.example.studyprojectrnc.data.dataSource.local.model

import io.realm.RealmObject

open class ModelImageRealm(
    var largeImageURL: String? = null,
    var views : Int? = null
) : RealmObject()
