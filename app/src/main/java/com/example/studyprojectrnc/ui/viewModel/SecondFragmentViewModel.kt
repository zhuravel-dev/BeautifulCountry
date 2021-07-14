package com.example.studyprojectrnc.ui.viewModel

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

    fun getData(query: String = "australia") {
        _showProgress.postValue(true)
        repository.getDataFromRemoteAndSaveToLocal(query) { models ->
            _showProgress.postValue(false)
            _models.postValue(models)
        }
    }
}

