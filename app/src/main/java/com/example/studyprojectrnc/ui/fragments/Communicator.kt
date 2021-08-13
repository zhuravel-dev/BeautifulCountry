package com.example.studyprojectrnc.ui.fragments

interface Communicator {
    fun passAndNavigateToSecondFragment(txtView: String)
    fun navigateToMapFragment()
    fun navigateToAnimationFragment()
    fun navigateToCameraFragment()
    fun navigateToBluetoothFragment()
}