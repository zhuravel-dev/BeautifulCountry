package com.example.studyprojectrnc.ui.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FirstFragmentTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun chekFirstFragmentVisibility() {
        onView(ViewMatchers.withId(R.id.layout_firstFragment))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun chekWelcomeTextVisibility() {
        onView(ViewMatchers.withId(R.id.tvWelcomeText))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun chekDatePickerVisibility() {
        onView(ViewMatchers.withId(R.id.tvDatePicker))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun chekLocationBtnVisibility() {
        onView(ViewMatchers.withId(R.id.btnGetLocation))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun chekMapBtnVisibility() {
        onView(ViewMatchers.withId(R.id.btnMap))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun chekBluetoothBtnVisibility() {
        onView(ViewMatchers.withId(R.id.btnBluetooth))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun chekCameraBtnVisibility() {
        onView(ViewMatchers.withId(R.id.btnCamera))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun chekNextBtnVisibility() {
        onView(ViewMatchers.withId(R.id.btnNext))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun chekClickToLocationBtn() {
        onView(ViewMatchers.withId(R.id.btnGetLocation))
            .perform(ViewActions.click())
    }

    @Test
    fun chekClickToMapBtn() {
        onView(ViewMatchers.withId(R.id.btnMap))
            .perform(ViewActions.click())
    }

    @Test
    fun chekClickToBluetoothBtn() {
        onView(ViewMatchers.withId(R.id.btnBluetooth))
            .perform(ViewActions.click())
    }

    @Test
    fun chekClickToCameraBtn() {
        onView(ViewMatchers.withId(R.id.btnCamera))
            .perform(ViewActions.click())
    }

    @Test
    fun chekClickToNextBtn() {
        onView(ViewMatchers.withId(R.id.btnNext))
            .perform(ViewActions.click())
    }

    @Test
    fun chekNavigateToMapFragment() {
        onView(ViewMatchers.withId(R.id.btnMap))
            .perform(ViewActions.click())

        onView(ViewMatchers.withId(R.id.layout_mapFragment))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun chekNavigateToBluetoothFragment() {
        onView(ViewMatchers.withId(R.id.btnBluetooth))
            .perform(ViewActions.click())

        onView(ViewMatchers.withId(R.id.layout_bluetoothFragment))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun chekNavigateToCameraFragment() {
        onView(ViewMatchers.withId(R.id.btnCamera))
            .perform(ViewActions.click())

        onView(ViewMatchers.withId(R.id.layout_cameraFragment))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun chekNavigateToSecondFragment() {
        onView(ViewMatchers.withId(R.id.btnNext))
            .perform(ViewActions.click())

        onView(ViewMatchers.withId(R.id.layout_secondFragment))
            .check(matches(ViewMatchers.isDisplayed()))
    }

}