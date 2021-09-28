package com.example.studyprojectrnc.presentation.firstScreen

import androidx.annotation.StringRes
import com.example.studyprojectrnc.presentation.base.BaseIntent

sealed class FirstFragmentIntent : BaseIntent() {

    class NavigateTo(@StringRes navigation: Int): FirstFragmentIntent()

}