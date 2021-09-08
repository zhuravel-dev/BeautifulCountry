package com.example.studyprojectrnc.data.repository

import androidx.paging.PagingSource
import com.example.studyprojectrnc.data.db.realm.ModelImageRealm
import com.example.studyprojectrnc.data.retrofit.ImagesServiceRetrofit
import com.example.studyprojectrnc.data.retrofit.model.ResponseDataList
import javax.inject.Inject

class ImagesRepositoryRealm @Inject constructor(private val api: ImagesServiceRetrofit) {
    private var pageNumber = 0

    suspend fun getDataFromRemoteAndSaveToLocal(params: PagingSource.LoadParams<Int>): TransitionResponse {
        val nextPage = params.key ?: 1
        val response = api.getContent(
            "22281764-aa17ceed19bc1ed0ef2893c10",
            "australia",
            "photo",
            1,
            10
        )
        return TransitionResponse(pageNumber++, response.toModelImageRealm())
    }
}

data class TransitionResponse(val nextPageNumber: Int, val listMM: List<ModelImageRealm>)

private fun ResponseDataList.toModelImageRealm(): List<ModelImageRealm> {
    return this.images?.map {
        ModelImageRealm(views = it.views, largeImageURL = it.largeImageURL)
    } ?: listOf()
}