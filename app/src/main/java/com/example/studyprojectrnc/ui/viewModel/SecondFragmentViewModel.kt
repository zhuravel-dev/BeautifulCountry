package com.example.studyprojectrnc.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studyprojectrnc.data.repository.ImagesRepository
import com.example.studyprojectrnc.data.db.ModelRealm

class SecondFragmentViewModel : ViewModel() {


    private val repository = ImagesRepository()


    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean> = _showProgress

    private val _models = MutableLiveData<List<ModelRealm>>()
    val models: LiveData<List<ModelRealm>> = _models


    fun getData(query: String = "forest") {
        _showProgress.postValue(true)
        repository.getDataFromRemoteAndSaveToLocal(query) { models ->
            _showProgress.postValue(false)
            _models.postValue(models)
        }
    }
}

