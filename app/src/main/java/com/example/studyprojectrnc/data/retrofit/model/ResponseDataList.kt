package com.example.studyprojectrnc.repository.model

import com.google.gson.annotations.SerializedName

data class ResponseDataList (
	@SerializedName("hits")
	var images: List<Response>? = null
)