package com.example.studyprojectrnc.presentation.di

import com.example.studyprojectrnc.data.ImagesRepositoryImpl
import com.example.studyprojectrnc.domain.repository.ImagesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNameRepository(nameRepositoryImpl: ImagesRepositoryImpl): ImagesRepository

}