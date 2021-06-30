package com.example.studyprojectrnc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studyprojectrnc.repository.model.Hits
import com.example.studyprojectrnc.repository.model.HitsDataList
import retrofit2.Call

class SecondFragmentViewModel : ViewModel() {
    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean> = _showProgress
    private val _getData = MutableLiveData<List<Hits>>()
    val getData: LiveData<List<Hits>> = _getData

    fun getAllData() {
        _showProgress.postValue(true)
        val service = RetrofitClientInstance.getRetrofitInstance().create(ImagesService::class.java)
        val call: Call<HitsDataList> = service.getContent()
        //call.enqueue()
        _showProgress.postValue(false)
    }
}
