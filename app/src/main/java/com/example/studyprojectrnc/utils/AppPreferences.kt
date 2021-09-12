package com.example.studyprojectrnc.utils

import android.content.SharedPreferences

class AppPreferences (private val prefs: SharedPreferences) {

    companion object {

        const val KEY_USER_NAME = "PREF_KEY_USER_NAME"

    }

    fun getUserName(): String? = prefs.getString(KEY_USER_NAME, null)

}