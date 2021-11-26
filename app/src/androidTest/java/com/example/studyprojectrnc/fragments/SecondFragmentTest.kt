package com.example.studyprojectrnc.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.launchFragmentInHiltContainer
import com.example.studyprojectrnc.presentation.secondScreen.SecondFragment
import com.example.studyprojectrnc.presentation.secondScreen.SecondFragmentVM_Factory
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SecondFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Inject
    lateinit var fragmentFactory: SecondFragmentVM_Factory

    @Test
    fun secondFragmentTest(){
        val scenario = launchFragmentInHiltContainer<SecondFragment>(
            factory = fragmentFactory
        )
    }

    @Test
    fun checkSecondFragmentVisibility() {
   //     val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.layout_secondFragment))
            .check(matches(isDisplayed()))
    }

    @Test
    fun isListFragmentVisibleOnAppLaunch() {
        onView(withId(R.id.rcView)).check(matches(isDisplayed()))
    }

    /*@Test
    fun listItemIsVisible() {
        onView(withId(R.id.rcView))
            .perform(actionOnItemAtPosition<ImageAdapter.ViewHolder>(LIST_ITEM_IN_TEST, ViewActions.click()))

    }*/
}