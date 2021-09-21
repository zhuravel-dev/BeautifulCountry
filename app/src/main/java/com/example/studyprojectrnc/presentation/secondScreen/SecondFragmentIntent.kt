package com.example.studyprojectrnc.presentation.secondScreen

import com.example.studyprojectrnc.presentation.base.BaseIntent

sealed class SecondFragmentIntent : BaseIntent() {

    object GetAllImages : SecondFragmentIntent()

}