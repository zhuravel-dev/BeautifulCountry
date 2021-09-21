package com.example.studyprojectrnc.domain.model

import com.example.studyprojectrnc.data.dataSource.local.model.ModelImageRealm

data class TransitionResponse(val nextPageNumber: Int, val listMM: List<ModelImageRealm>)