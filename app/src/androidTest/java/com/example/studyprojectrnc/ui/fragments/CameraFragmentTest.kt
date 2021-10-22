package com.example.studyprojectrnc.ui.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CameraFragmentTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkCameraFragmentVisibility() {
        onView(withId(R.id.layout_cameraFragment))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkCameraBtnVisibility() {
       onView(withId(R.id.fabAddPhoto))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkSwitchBtnVisibility() {
        onView(withId(R.id.fabSwitch))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkGalleryBtnVisibility() {
        onView(withId(R.id.ibGallery))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkClickOnSwitchBtn() {
        onView(withId(R.id.fabSwitch))
            .perform(click())
    }

    @Test
    fun checkClickOnCameraBtn() {
        onView(withId(R.id.btnCamera))
            .perform(click())
    }

    @Test
    fun checkClickOnGalleryBtn() {
        onView(withId(R.id.ibGallery))
            .perform(click())
    }
    @Test
    fun checkNavigateToGalleryFragment() {
        onView(withId(R.id.ibGallery))
            .perform(click())

        onView(withId(R.id.layout_gallery))
            .check(matches(isDisplayed()))
    }
}