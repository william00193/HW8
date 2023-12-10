package com.example.hw8exercise1.api

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "38966820aba3820d74f6b3121335fe3d"

interface FlickrAPI {



    @GET(

        "services/rest/?method=flickr.interestingness.getList" +
                "&api_key=$API_KEY" +
                "&format=json" +
                "&page=4"+
                "&nojsoncallback=1" +
                "&extras=url_s"
    )


    // suspend fun fetchContents(): String
    suspend fun fetchPhotos(): FlickrResponse

}
