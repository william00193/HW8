package com.example.hw8exercise1.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//Data class that is defining the 'GalleryItem'
@JsonClass(generateAdapter = true)
data class PhotoResponse(
    @Json(name = "photo") val galleryItems: List<GalleryItem>
)

