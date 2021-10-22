package com.example.studyprojectrnc.ui.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FirstFragmentTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkFirstFragmentVisibility() {
        onView(withId(R.id.layout_firstFragment))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkWelcomeTextVisibility() {
        onView(withId(R.id.tvWelcomeText))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkDataPickerVisibility() {
        onView(withId(R.id.dataPicker))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkLocationBtnVisibility() {
        onView(withId(R.id.btnGetLocation))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkMapBtnVisibility() {
        onView(withId(R.id.btnMap))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkBluetoothBtnVisibility() {
        onView(withId(R.id.btnBluetooth))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkCameraBtnVisibility() {
        onView(withId(R.id.btnCamera))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkNextBtnVisibility() {
        onView(withId(R.id.btnNext))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkClickOnLocationBtn() {
        onView(withId(R.id.btnGetLocation))
            .perform(click())
    }

    @Test
    fun checkClickOnMapBtn() {
        onView(withId(R.id.btnMap))
            .perform(click())
    }

    @Test
    fun checkClickOnBluetoothBtn() {
        onView(withId(R.id.btnBluetooth))
            .perform(click())
    }

    @Test
    fun checkClickOnCameraBtn() {
        onView(withId(R.id.btnCamera))
            .perform(click())
    }

    @Test
    fun checkClickOnNextBtn() {
        onView(withId(R.id.btnNext))
            .perform(click())
    }

    @Test
    fun checkNavigateToMapFragment() {
        onView(withId(R.id.btnMap))
            .perform(click())

        onView(withId(R.id.layout_mapFragment))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkNavigateToBluetoothFragment() {
        onView(withId(R.id.btnBluetooth))
            .perform(click())

        onView(withId(R.id.layout_bluetoothFragment))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkNavigateToCameraFragment() {
        onView(withId(R.id.btnCamera))
            .perform(click())

        onView(withId(R.id.layout_cameraFragment))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkNavigateToSecondFragment() {
        onView(withId(R.id.btnNext))
            .perform(click())

        onView(withId(R.id.layout_secondFragment))
            .check(matches(isDisplayed()))
    }
}