package com.example.studyprojectrnc.repository.model

import com.google.gson.annotations.SerializedName


data class HitsDataList (

	var total: Long = 0,
	var totalHits: Long = 0,
	@SerializedName("hits")
	var images: List<Hits>? = null

)
