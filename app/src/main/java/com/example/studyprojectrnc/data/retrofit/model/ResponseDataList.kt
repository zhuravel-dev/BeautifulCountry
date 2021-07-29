package com.example.studyprojectrnc.data.retrofit.model

import com.google.gson.annotations.SerializedName

data class ResponseDataList (
	@SerializedName("hits")
	var images: List<Response>? = null
)