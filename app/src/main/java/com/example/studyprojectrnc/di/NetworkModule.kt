package com.example.studyprojectrnc.di

import android.content.Context
import androidx.room.Room
import com.example.studyprojectrnc.data.db.room.RoomDB
import com.example.studyprojectrnc.data.retrofit.ImagesServiceRetrofit
import com.example.studyprojectrnc.data.retrofit.ImagesServiceRetrofitImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration

@Module
@InstallIn(SingletonComponent::class)
open class NetworkModule {

    @Provides
    fun providePixabayApi () : ImagesServiceRetrofit {
        return ImagesServiceRetrofitImpl.provideService()
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