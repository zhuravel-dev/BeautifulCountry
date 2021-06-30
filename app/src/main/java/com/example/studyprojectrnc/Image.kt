package com.example.studyprojectrnc

import com.google.gson.annotations.SerializedName

data class Image (
    @SerializedName("largeImageURL") val largeImageURL: String?,
    @SerializedName("previewURL") val previewURL: String?,
    @SerializedName("order") val order: String
)
