package com.example.studyprojectrnc.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studyprojectrnc.data.db.realm.ModelImageRealm
import timber.log.Timber

class SecondFragmentViewModel() : ViewModel() {
 //   private val repository = ImagesRepositoryRealm()
    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean> = _showProgress
    private val _models = MutableLiveData<List<ModelImageRealm>>()
    val models: LiveData<List<ModelImageRealm>> = _models

    fun fetchData(query: String = "australia") {
        Timber.i("In ViewModel, fun fetchData")

        _showProgress.postValue(true)
    }
}