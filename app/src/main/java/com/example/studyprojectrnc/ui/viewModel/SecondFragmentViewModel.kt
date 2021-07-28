package com.example.studyprojectrnc.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studyprojectrnc.data.repository.ImagesRepositoryRealm
import com.example.studyprojectrnc.data.realmForImage.ModelImageRealm

class SecondFragmentViewModel : ViewModel() {
    private val repository = ImagesRepositoryRealm()
    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean> = _showProgress
    private val _models = MutableLiveData<List<ModelImageRealm>>()
    val models: LiveData<List<ModelImageRealm>> = _models

    fun fetchData(query: String = "australia") {
        Log.i("TAG", "In fun fetchData")
        _showProgress.postValue(true)
    }
}

