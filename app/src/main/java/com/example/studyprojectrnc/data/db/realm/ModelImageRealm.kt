package com.example.studyprojectrnc.data.db.realm

import io.realm.RealmObject

open class ModelImageRealm(
  //  var num: Int = 0,
    var largeImageURL: String? = null,
    var views : Int? = null
) : RealmObject()
