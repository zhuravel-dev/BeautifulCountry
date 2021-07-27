package com.example.studyprojectrnc.ui.paging3

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.studyprojectrnc.data.realmForImage.ModelImageRealm
import com.example.studyprojectrnc.data.repository.ImagesRepositoryRealm
import com.example.studyprojectrnc.data.retrofit.ImagesServiceRetrofit
import com.example.studyprojectrnc.repository.model.ResponseDataList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//TOTO must be service: ImagesRepositoryRealm
class ImagePaging(private val service: ImagesRepositoryRealm) :
    PagingSource<Int, ModelImageRealm>() {

    override fun getRefreshKey(state: PagingState<Int, ModelImageRealm>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>)
            : LoadResult<Int, ModelImageRealm> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.getDataFromRemoteAndSaveToLocal()
            LoadResult.Page(
                data = response.listMM,
                prevKey = null,
                nextKey = response.nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

