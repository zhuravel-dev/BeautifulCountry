package com.example.studyprojectrnc.data.repository

import android.net.Uri
import androidx.paging.PagingSource
import com.example.studyprojectrnc.data.retrofit.ImagesServiceRetrofit
import com.example.studyprojectrnc.data.retrofit.RetrofitClientInstance
import com.example.studyprojectrnc.data.local.LocalSource
import com.example.studyprojectrnc.data.realmForImage.ModelImageRealm
import com.example.studyprojectrnc.repository.model.ResponseDataList
import java.util.concurrent.Executors

class ImagesRepositoryRealm {

    private val localSource = LocalSource()
    private val remoteSource =
        RetrofitClientInstance.getRetrofitInstance().create(ImagesServiceRetrofit::class.java)
    private val execService = Executors.newSingleThreadExecutor()

    suspend fun getDataFromRemoteAndSaveToLocal(params: PagingSource.LoadParams<Int>): TransitionResponse {
        val nextPage = params.key ?: 1
        val response = remoteSource.getContent(
            "22281764-aa17ceed19bc1ed0ef2893c10",
            "australia",
            "photo",
             nextPage,
            20
        )

        val pagedResponse = response.body()
        val data = pagedResponse?.results

        var nextPageNumber: Int? = null
        if (pagedResponse?.pageInfo?.next != null) {
            val uri = Uri.parse(pagedResponse.pageInfo.next)
            val nextPageQuery = uri.getQueryParameter("page")
            nextPageNumber = nextPageQuery?.toInt()
        }

        //data  -> store to db
       // (data.orEmpty().map { it.toModelImageRealm() } )
            // <---mapping )
        //get from db and you receive ModelImageRealm

        return TransitionResponse(nextPageNumber!!, data.orEmpty().map { it.toModelImageRealm() })
    }
}

data class TransitionResponse(val nextPageNumber: Int, val listMM: List<ModelImageRealm>)

private fun ResponseDataList.toModelImageRealm(): ModelImageRealm {
    TODO("Not yet implemented")
}

