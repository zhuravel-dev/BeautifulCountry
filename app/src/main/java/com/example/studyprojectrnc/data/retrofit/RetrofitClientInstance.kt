package com.example.studyprojectrnc.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// fix
object RetrofitClientInstance {
    private const val baseUrl = "https://pixabay.com/api/"
    fun getRetrofitInstance () = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}