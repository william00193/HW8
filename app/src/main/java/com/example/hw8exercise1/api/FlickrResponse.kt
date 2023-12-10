package com.example.hw8exercise1.api

import com.squareup.moshi.JsonClass

//What is defining the entire response from Flickr
@JsonClass(generateAdapter = true)
data class FlickrResponse(
    val photos: PhotoResponse
)