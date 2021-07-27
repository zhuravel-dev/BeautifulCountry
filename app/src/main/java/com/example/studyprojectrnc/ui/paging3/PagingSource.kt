package com.example.studyprojectrnc.ui.paging3

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.studyprojectrnc.data.realmForImage.ModelImageRealm
import com.example.studyprojectrnc.data.retrofit.ImagesServiceRetrofit

class ImagePaging(private val service: ImagesServiceRetrofit)
    : PagingSource<Int, ModelImageRealm>(){

    override fun getRefreshKey(state: PagingState<Int, ModelImageRealm>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>)
    : LoadResult<Int, ModelImageRealm> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.getContent("22281764-aa17ceed19bc1ed0ef2893c10", "australia", "photo", pageNumber, 20)
            val pagedResponse = response.body()
            val data = pagedResponse?.results

            var nextPageNumber: Int? = null
            if (pagedResponse?.pageInfo?.next != null) {
                val uri = Uri.parse(pagedResponse.pageInfo.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}