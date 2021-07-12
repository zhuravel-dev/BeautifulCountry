package com.example.studyprojectrnc.data.repository

import com.example.studyprojectrnc.data.retrofit.ImagesServiceRetrofit
import com.example.studyprojectrnc.data.retrofit.RetrofitClientInstance
import com.example.studyprojectrnc.data.local.LocalSource
import com.example.studyprojectrnc.data.realmForImage.ModelImageRealm
import com.example.studyprojectrnc.repository.model.HitsDataList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class ImagesRepositoryRealm {

    private val localSource = LocalSource()
    private val remoteSource = RetrofitClientInstance.getRetrofitInstance().create(ImagesServiceRetrofit::class.java)
    private val execService = Executors.newSingleThreadExecutor()

    fun getDataFromRemoteAndSaveToLocal(q: String, callback: (List<ModelImageRealm>) -> Unit) {
        execService.submit {
            remoteSource.getContent(q = q).enqueue(object : Callback<HitsDataList> {
                override fun onResponse(call: Call<HitsDataList>, response: Response<HitsDataList>) {
                    if(response.isSuccessful) localSource.saveImageRealmObjects(response.body()?.images, callback)
                    else callback.invoke(listOf())
                }

                override fun onFailure(call: Call<HitsDataList>, t: Throwable) {
                    callback.invoke(listOf())
                }
            })
        }
    }
}