package com.example.studyprojectrnc.fragments

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.presentation.MainActivity
import com.example.studyprojectrnc.presentation.camera.CameraFragment
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CameraFragmentTest {

    @Test
    @Before
    fun init() {
        FragmentScenario.Companion.launchInContainer(
            CameraFragment::class.java,
            null,
            R.style.AppTheme,
            null
        )
    }

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

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.btnCamera))
            .perform(click())
    }

    @Test
    fun checkClickOnGalleryBtn() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.btnCamera))
            .perform(click())

        onView(withId(R.id.ibGallery))
            .perform(click())
    }

   /* @Test
    fun thumbnailIsNotDrawn() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.previewView)).check(doesNotExist())

    }*/

}