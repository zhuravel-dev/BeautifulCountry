package com.example.studyprojectrnc.data.repository

import com.example.studyprojectrnc.data.dataSource.local.model.ModelImageRealm
import com.example.studyprojectrnc.domain.DataState
import com.example.studyprojectrnc.domain.model.TransitionResponse
import com.example.studyprojectrnc.domain.repository.ImagesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class ImagesRepositoryImplTest {

    @ExperimentalCoroutinesApi
    @Test
    fun mockServerResponse() = runBlockingTest {
        val api: ImagesRepository = mock()
        Mockito.`when`(api.getDataFromRemoteAndSaveToLocal()).doReturn(
            flow {
                emit(
                    DataState.Success(
                        TransitionResponse(
                            0, listOf(ModelImageRealm("", 0)
                            ))))
            }
        )

        api.getDataFromRemoteAndSaveToLocal().collect {
            assertTrue(it is DataState.Success)
            assertTrue((it as DataState.Success).data.listMM.isNotEmpty())
            assertEquals(
                it.data, TransitionResponse(
                    0, listOf(
                        ModelImageRealm("", 0)
                    )))
        }
    }
}