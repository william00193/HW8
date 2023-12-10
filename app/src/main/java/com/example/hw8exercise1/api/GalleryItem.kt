package com.example.hw8exercise1.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//Data class that is defining the attributes in my PhotoResponse
@JsonClass(generateAdapter = true)

data class GalleryItem(
    val title: String,
    val id: String,
    @Json(name = "url_s") val url: String,
)
