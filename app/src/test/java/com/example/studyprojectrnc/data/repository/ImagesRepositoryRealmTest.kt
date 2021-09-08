package com.example.studyprojectrnc.data.repository

import androidx.paging.PagingSource
import com.example.studyprojectrnc.MainCoroutineRule
import com.example.studyprojectrnc.data.retrofit.ImagesServiceRetrofit
import com.example.studyprojectrnc.data.retrofit.model.ResponseDataList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ImagesRepositoryRealmTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @ExperimentalCoroutinesApi
    @Test
    fun testGetDataFromRemoteAndSaveToLocal() = runBlockingTest {
        val response = ResponseDataList(listOf(), listOf())
        val api: ImagesServiceRetrofit = mock()
        whenever(api.getContent(anyString(), anyString(), anyString(), anyInt(), anyInt())).thenReturn(response)
        val imagesRepo = ImagesRepositoryRealm(api)
        val params: PagingSource.LoadParams<Int> = mock()
        val answer = TransitionResponse(0, response.toModelImageRealm())
        Assert.assertEquals(imagesRepo.getDataFromRemoteAndSaveToLocal(params), answer)
    }
}