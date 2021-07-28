package com.example.studyprojectrnc.data.retrofit

import com.example.studyprojectrnc.BuildConfig
import com.example.studyprojectrnc.repository.model.ResponseDataList
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesServiceRetrofit {
    @GET("/api/")
    suspend fun getContent(
        @Query("key") key: String = BuildConfig.key,
        @Query("q") q: String = BuildConfig.q,
        @Query("image_type") imageType: String = BuildConfig.imageType,
        @Query("page") page: Int?,
        @Query("per_page") per_page: Int?
    ): Response<PagedResponse<ResponseDataList>>
}

data class PagedResponse<T>(
    @SerializedName("info") val pageInfo: PageInfo,
    val results: List<T> = listOf()
)

data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
