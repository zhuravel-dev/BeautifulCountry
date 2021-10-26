package com.example.studyprojectrnc.fragments

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.presentation.MainActivity
import com.example.studyprojectrnc.presentation.camera.CameraX
import com.example.studyprojectrnc.presentation.secondScreen.SecondFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SecondFragmentTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    @Before
    fun init() {
        FragmentScenario.Companion.launchInContainer(
            SecondFragment::class.java,
            null,
            R.style.AppTheme,
            null
        )
    }

    @Test
    fun checkSecondFragmentVisibility() {
   //     val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.layout_secondFragment))
            .check(matches(isDisplayed()))
    }
}