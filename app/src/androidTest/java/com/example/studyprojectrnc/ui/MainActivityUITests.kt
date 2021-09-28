package com.example.studyprojectrnc.ui

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
class MainActivityUITests {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun chekActivityVisibility(){
        onView(withId(R.id.layout_mainActivity))
            .check(matches(isDisplayed()))
    }

    @Test
    fun navigateToMapFragment(){
        onView(withId(R.id.btnMap))
            .perform(click())
    }

    @Test
    fun navigateToBluetoothFragment(){
        onView(withId(R.id.btnBluetooth))
            .perform(click())
    }

    @Test
    fun navigateToCameraFragment(){
        onView(withId(R.id.btnCamera))
            .perform(click())
    }

}