package com.example.studyprojectrnc.fragments

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.presentation.camera.GalleryFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class GalleryFragmentTest {

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

}