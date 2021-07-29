package com.example.studyprojectrnc.data.realmForImage

import io.realm.RealmObject

open class ModelImageRealm(
    var num: Int = 0,
 // var largeImageURL: String? = null
    var previewURL : String? = null,
    var likes : Int = 0
) : RealmObject()
