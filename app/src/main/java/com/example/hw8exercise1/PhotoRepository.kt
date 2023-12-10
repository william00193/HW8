package com.example.hw8exercise1

import androidx.paging.*
import com.example.hw8exercise1.api.FlickrAPI
import com.example.hw8exercise1.api.GalleryItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create


//In my PhotoRepository page a lot of things have changed
//especially regarding the paging source and how results are loaded
class PhotoRepository(private val flickrApi: FlickrAPI) {



//This is my paging source library that was the help of Android Studio Bot and the Android developers page
//This is allowing me to define my prevKey and nextKey as well as LoadResult.Page
class PhotoPagingSource(private val flickrApi: FlickrAPI) : PagingSource<Int, GalleryItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryItem> {
        try {
            val page = params.key ?: 1
            val response = flickrApi.fetchPhotos()
            val photos = response.photos.galleryItems

            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (photos.isNotEmpty()) page + 1 else null

            return LoadResult.Page(
                data = photos,
                prevKey = prevKey,
                nextKey = nextKey

            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }


//This is my getRefreshKey that is allowing me to refresh the page and get new photos
//This was also a suggestion from Android Studio Bot that worked with the code I had already
    override fun getRefreshKey(state: PagingState<Int, GalleryItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
}