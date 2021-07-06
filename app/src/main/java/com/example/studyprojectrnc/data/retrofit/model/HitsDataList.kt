package com.example.studyprojectrnc.repository.model

import com.google.gson.annotations.SerializedName

data class HitsDataList (
	@SerializedName("hits")
	var images: List<Hits>? = null
)