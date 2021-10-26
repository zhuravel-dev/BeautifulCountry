package com.example.studyprojectrnc.fragments

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.presentation.MainActivity
import com.example.studyprojectrnc.presentation.camera.GalleryFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GalleryFragmentTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    @Before
    fun init() {
        FragmentScenario.Companion.launchInContainer(
            GalleryFragment::class.java,
            null,
            R.style.AppTheme,
            null
        )
    }

    @Test
    fun checkGalleryFragmentVisibility() {
        onView(withId(R.id.layout_gallery))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkNavigateBackToCameraFragment() {
        onView(withId(R.id.btnBackFromGalleryToCamera))
            .perform(ViewActions.click())

        onView(withId(R.id.layout_cameraFragment))
            .check(matches(isDisplayed()))
    }
}