package com.example.studyprojectrnc.data.realmforImage
import io.realm.RealmObject

open class ModelImageRealm (
    var num : Int = 0,
    var largeImageURL : String? = null
) : RealmObject()
