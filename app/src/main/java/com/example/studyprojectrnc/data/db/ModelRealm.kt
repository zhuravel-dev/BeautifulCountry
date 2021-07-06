package com.example.studyprojectrnc.data.db
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ModelRealm (
    var num : Int = 0,
    var largeImageURL : String? = null
) : RealmObject()
