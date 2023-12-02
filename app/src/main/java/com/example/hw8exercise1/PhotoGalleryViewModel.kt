package com.example.hw8exercise1

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import androidx.recyclerview.widget.RecyclerView
import com.example.hw8exercise1.api.FlickrAPI
import com.example.hw8exercise1.api.GalleryItem
import com.example.hw8exercise1.api.PhotoResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

private const val TAG = "PhotoGalleryViewModel"


//PhotoGalleryViewModel function that needs to work

class PhotoGalleryViewModel: ViewModel() {

        private val flickrApi: FlickrAPI = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(FlickrAPI::class.java)

        fun getPhotos(): Flow<PagingData<GalleryItem>> {
           return Pager(
                config = PagingConfig(
                    pageSize = 100,
                    enablePlaceholders = true
                ),
                pagingSourceFactory = { PhotoRepository.PhotoPagingSource(flickrApi) }
            ).flow

        }
}

