package com.example.studyprojectrnc.repository.model

import com.google.gson.annotations.SerializedName


data class Json4Kotlin_Base (

	var total: Long = 0,
	var totalHits: Long = 0,
	@SerializedName("hits")
	var images: List<Hits>? = null

)