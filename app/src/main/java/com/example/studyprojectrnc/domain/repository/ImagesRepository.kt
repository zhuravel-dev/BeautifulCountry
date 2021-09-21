package com.example.studyprojectrnc.domain.repository

import com.example.studyprojectrnc.domain.DataState
import com.example.studyprojectrnc.domain.model.TransitionResponse
import kotlinx.coroutines.flow.Flow

interface ImagesRepository {

    suspend fun getDataFromRemoteAndSaveToLocal(): Flow<DataState<TransitionResponse>>

}