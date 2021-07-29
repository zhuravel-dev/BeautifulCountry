package com.example.studyprojectrnc.data.repository

import android.net.Uri
import androidx.paging.PagingSource
import com.example.studyprojectrnc.data.retrofit.ImagesServiceRetrofit
import com.example.studyprojectrnc.data.retrofit.RetrofitClientInstance
import com.example.studyprojectrnc.data.local.LocalSource
import com.example.studyprojectrnc.data.realmForImage.ModelImageRealm
import com.example.studyprojectrnc.data.retrofit.PageInfo
import com.example.studyprojectrnc.data.retrofit.PagedResponse
import com.example.studyprojectrnc.data.retrofit.model.Response
import com.example.studyprojectrnc.data.retrofit.model.ResponseDataList
import java.util.concurrent.Executors

class ImagesRepositoryRealm {
    private var pageNumber = 0
    private val remoteSource =
        RetrofitClientInstance.getRetrofitInstance().create(ImagesServiceRetrofit::class.java)

    suspend fun getDataFromRemoteAndSaveToLocal(params: PagingSource.LoadParams<Int>): TransitionResponse {
        val nextPage = params.key ?: 1
        val response = remoteSource.getContent(
            "22281764-aa17ceed19bc1ed0ef2893c10",
            "australia",
            "photo",
             nextPage,
            20
        )

        return TransitionResponse(pageNumber++, response.toModelImageRealm())
    }
}

data class TransitionResponse(val nextPageNumber: Int, val listMM: List<ModelImageRealm>)

private fun ResponseDataList.toModelImageRealm(): List<ModelImageRealm> {
    return this.images?.map {
        ModelImageRealm(num = it.id, previewURL = it.previewURL)
    } ?: listOf()
}