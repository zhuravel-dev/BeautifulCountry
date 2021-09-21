package com.example.studyprojectrnc.data

import com.example.studyprojectrnc.data.dataSource.remote.ImagesServiceRetrofit
import com.example.studyprojectrnc.domain.DataState
import com.example.studyprojectrnc.domain.model.TransitionResponse
import com.example.studyprojectrnc.domain.repository.ImagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ImagesRepositoryImpl @Inject constructor(private val api: ImagesServiceRetrofit):
    ImagesRepository {
    private var pageNumber = 0

    override suspend fun getDataFromRemoteAndSaveToLocal(): Flow<DataState<TransitionResponse>> = flow {
        emit(DataState.Loading)
        try {
            val response = api.getContent(
                "22281764-aa17ceed19bc1ed0ef2893c10",
                "australia",
                "photo",
                1,
                10
            )
            if(response.images.isNullOrEmpty()) emit(DataState.Empty)
            else {
                val model = TransitionResponse(pageNumber++, response.toModelImageRealm())
                emit(DataState.Success(model))
            }
        } catch (e: Throwable) {
            emit(DataState.Error(e as Exception))
        }
    }
}