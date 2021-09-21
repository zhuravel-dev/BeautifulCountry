/*package com.example.studyprojectrnc.ui.paging3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.studyprojectrnc.data.dataSource.local.model.ModelImageRealm
import com.example.studyprojectrnc.domain.repository.ImagesRepository


class ImagePaging(private val service: ImagesRepository) :
    PagingSource<Int, ModelImageRealm>() {

    override fun getRefreshKey(state: PagingState<Int, ModelImageRealm>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>)
            : LoadResult<Int, ModelImageRealm> {
        return try {
            val response = service.getDataFromRemoteAndSaveToLocal(params)
            LoadResult.Page(
                data = response.listMM,
                prevKey = null,
                nextKey = response.nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}*/
