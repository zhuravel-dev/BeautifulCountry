package com.example.studyprojectrnc.presentation.di

import com.example.studyprojectrnc.data.dataSource.remote.ImagesServiceRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
open class NetworkModule {

    @Provides
    fun providePixabayApi () : ImagesServiceRetrofit {
        val baseUrl = "https://pixabay.com/api/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ImagesServiceRetrofit::class.java)
    }
}