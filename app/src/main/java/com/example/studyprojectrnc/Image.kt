package com.example.studyprojectrnc

import com.google.gson.annotations.SerializedName


sealed class SealedImage(val value: String?){
    class largeURL:SealedImage("largeImageURL")
    class previewURL:SealedImage("previewURL")
    class order:SealedImage("order")
}

data class Image (
    @SerializedName("largeImageURL") val largeImageURL: String?,
    @SerializedName("previewURL") val previewURL: String?,
    @SerializedName("order") val order: String
)
