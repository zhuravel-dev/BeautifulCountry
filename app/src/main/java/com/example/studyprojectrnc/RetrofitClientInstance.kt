package com.example.studyprojectrnc

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    val baseUrl = "https://pixabay.com/api/"
    fun getRetrofitInstance () = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}