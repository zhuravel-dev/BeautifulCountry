package com.example.studyprojectrnc.utils.log

import com.example.studyprojectrnc.utils.AppPreferences
import timber.log.Timber

class DebugTree (private val appPreferences: AppPreferences) : Timber.DebugTree() {

    companion object {
        const val Priority = "priority"
        const val Tag = "tag"
    }

    override fun createStackElementTag(element: StackTraceElement): String? =
        "(${element.fileName}:${element.lineNumber}) ${element.methodName}"

}