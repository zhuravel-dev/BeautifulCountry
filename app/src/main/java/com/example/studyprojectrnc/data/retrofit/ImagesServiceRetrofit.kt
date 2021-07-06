package com.example.studyprojectrnc.data.retrofit

import com.example.studyprojectrnc.BuildConfig
import com.example.studyprojectrnc.repository.model.HitsDataList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesServiceRetrofit {
    @GET("/api/")
    fun getContent(
        @Query("key") key: String = BuildConfig.key,
        @Query("q") q: String = BuildConfig.q,
        @Query("image_type") imageType: String = BuildConfig.imageType
    ): Call<HitsDataList>
}

