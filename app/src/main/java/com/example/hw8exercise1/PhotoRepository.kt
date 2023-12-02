package com.example.hw8exercise1

import androidx.paging.*
import com.example.hw8exercise1.api.FlickrAPI
import com.example.hw8exercise1.api.GalleryItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class PhotoRepository(private val flickrApi: FlickrAPI) {


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

    override fun getRefreshKey(state: PagingState<Int, GalleryItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
}