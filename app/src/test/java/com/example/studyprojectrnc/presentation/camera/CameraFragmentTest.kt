/*
package com.example.studyprojectrnc.presentation.camera

import android.Manifest
import android.content.Context
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.example.studyprojectrnc.presentation.MainActivity
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class CameraFragmentTest {

    @get:Rule
    val permissionRule = GrantPermissionRule.grant(
        Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Mock
    lateinit var cameraProviderFuture: ProcessCameraProvider
    lateinit var processCameraProvider: ProcessCameraProvider
    private lateinit var mockContext: Context

    @Test
    fun useAppContext() {
        val context = ApplicationProvider.getApplicationContext() as Context
        assertEquals("com.android.example.studyprojectrnc", context.packageName)
    }

    @Test
    fun testStartCamera() {

        //init
        val cameraFragment: CameraFragment = mock()
        val result = cameraFragment.startCamera()

        Mockito.`when`(result).doReturn {

        }

        //work

        //assert
        Assert.assertEquals(result)

    }
}*/
