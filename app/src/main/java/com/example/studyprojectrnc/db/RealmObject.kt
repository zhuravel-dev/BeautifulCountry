package com.example.studyprojectrnc.db
import io.realm.RealmObject

data class RealmObject (
    val id : Int = 0,
    val largeImageURL : String? = null
) : RealmObject()
