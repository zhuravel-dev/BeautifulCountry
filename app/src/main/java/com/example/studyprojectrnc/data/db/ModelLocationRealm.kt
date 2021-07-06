package com.example.studyprojectrnc.data.db

import io.realm.RealmObject

open class ModelLocationRealm(
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var altitude: Double = 0.0,
) : RealmObject()