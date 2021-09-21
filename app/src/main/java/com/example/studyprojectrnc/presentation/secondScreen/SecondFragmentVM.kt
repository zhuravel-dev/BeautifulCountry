package com.example.studyprojectrnc.presentation.secondScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.studyprojectrnc.domain.DataState
import com.example.studyprojectrnc.domain.model.TransitionResponse
import com.example.studyprojectrnc.domain.repository.ImagesRepository
import com.example.studyprojectrnc.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondFragmentVM @Inject constructor(private val repository: ImagesRepository) : BaseViewModel<SecondFragmentIntent>() {

    private var _imagesLiveData = MutableLiveData<DataState<TransitionResponse>>()
    val imagesLiveData: LiveData<DataState<TransitionResponse>> = _imagesLiveData

    override fun onTriggerEvent(eventType: SecondFragmentIntent) {
        viewModelScope.launch {
            when (eventType) {
                is SecondFragmentIntent.GetAllImages -> getAllImagesFromRepoitory()
            }
        }
    }

    private suspend fun getAllImagesFromRepoitory() {
        repository.getDataFromRemoteAndSaveToLocal().collect {
            _imagesLiveData.postValue(it)
        }
    }
}