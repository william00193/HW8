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



//PhotoGalleryViewModel class that extends ViewModel
//This part is in control of my pager and paging source
class PhotoGalleryViewModel: ViewModel() {

        private val flickrApi: FlickrAPI = Retrofit.Builder()

//Base Url for the API
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(FlickrAPI::class.java)


//Function/Pager that is getting the photos from the API and defining the page size
        fun getPhotos(): Flow<PagingData<GalleryItem>> {
           return Pager(
                config = PagingConfig(
                    pageSize = 100,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = {
                    PhotoRepository.PhotoPagingSource(flickrApi)
                }
            ).flow

        }
}

