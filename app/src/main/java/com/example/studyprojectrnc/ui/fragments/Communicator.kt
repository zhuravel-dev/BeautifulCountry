package com.example.studyprojectrnc.ui.fragments

import java.net.URL

interface Communicator {
    fun passAndNavigateToSecondFragment(txtView: String)
    fun navigateToMapFragment()
    fun navigateToAnimationFragment()
}