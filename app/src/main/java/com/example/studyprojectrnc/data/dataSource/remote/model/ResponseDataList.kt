package com.example.studyprojectrnc.data.dataSource.remote.model

import com.example.studyprojectrnc.data.dataSource.local.model.ModelImageRealm
import com.google.gson.annotations.SerializedName

data class ResponseDataList (
	@SerializedName("hits")
	var images: List<Response>? = null,
	var views: List<Response>? = null
) {
	fun toModelImageRealm(): List<ModelImageRealm> {
		return this.images?.map {
			ModelImageRealm(views = it.views, largeImageURL = it.largeImageURL)
		} ?: listOf()
	}
}