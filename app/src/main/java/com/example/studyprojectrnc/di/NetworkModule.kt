package com.example.studyprojectrnc.di

import android.content.Context
import androidx.room.Room
import com.example.studyprojectrnc.data.db.room.RoomDB
import com.example.studyprojectrnc.data.retrofit.ImagesServiceRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
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

    @Provides
    fun provideRoom(context: Context) : RoomDB {
        return Room.databaseBuilder(context, RoomDB::class.java, "LOCATION_DATABASE").build()
    }

    @Provides
    fun provideRealmConfiguration(): RealmConfiguration {
        return RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
    }

    @Provides
    fun provideDefaultRealm(config: RealmConfiguration): Realm? {
        return Realm.getInstance(config)
    }
}