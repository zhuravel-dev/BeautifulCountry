package com.example.studyprojectrnc

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesService {
    @GET("/search/photos")
    fun getContent(
        @Query("key") key: String = "22281764-aa17ceed19bc1ed0ef2893c10",
        @Query("q") q: String = "forest",
        @Query("image_type") imageType: String = "photo"
    ): Call<SealedImage>
}