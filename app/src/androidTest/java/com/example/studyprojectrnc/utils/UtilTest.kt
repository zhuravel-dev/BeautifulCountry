package com.example.studyprojectrnc.utils

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.ui.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UtilTestUI {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun chekActivityVisibility(){
        onView(withId(R.id.layout_mainActivity))
            .check(matches(isDisplayed()))
    }

    @Test
    fun chekFirstFragmentVisibility(){
        onView(withId(R.id.layout_firstFragment))
            .check(matches(isDisplayed()))
    }

    @Test
    fun chekTextVisibility(){
        onView(withId(R.id.tvWelcomeText))
            .check(matches(isDisplayed()))
    }

    @Test
    fun chekLocationBtnVisibility(){
        onView(withId(R.id.btnGetLocation))
            .check(matches(isDisplayed()))
    }

    @Test
    fun chekMapBtnVisibility(){
        onView(withId(R.id.btnMap))
            .check(matches(isDisplayed()))
    }

    @Test
    fun chekBluetoothBtnVisibility(){
        onView(withId(R.id.btnBluetooth))
            .check(matches(isDisplayed()))
    }

    @Test
    fun chekCameraBtnVisibility(){
        onView(withId(R.id.btnCamera))
            .check(matches(isDisplayed()))
    }

    @Test
    fun chekNextBtnVisibility(){
        onView(withId(R.id.btnNext))
            .check(matches(isDisplayed()))
    }

    @Test
    fun chekWelcomeText(){
        onView(withId(R.id.tvWelcomeText))
            .check(matches(withText(R.string.welcome)))
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

    @Test
    fun navigateToSecondFragment(){
        onView(withId(R.id.btnNext))
            .perform(click())

        onView(withId(R.id.layout_secondFragment))
            .check(matches(isDisplayed()))
    }

}