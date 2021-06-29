package com.example.studyprojectrnc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call

class CustomViewModel : ViewModel() {
    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean> = _showProgress
    private val _setData = MutableLiveData<List<SealedImage>>()
    val setData: LiveData<List<SealedImage>> = _setData

    fun getAllData() {
        _showProgress.postValue(true)
        val service = RetrofitClientInstance.getRetrofitInstance().create(ImagesService::class.java)
        val call: Call<SealedImage> = service.getContent()
        _showProgress.postValue(false)
    }
}