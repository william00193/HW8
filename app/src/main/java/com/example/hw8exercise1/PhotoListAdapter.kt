package com.example.hw8exercise1

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hw8exercise1.api.GalleryItem
import com.example.hw8exercise1.databinding.ListItemGalleryBinding
import java.util.*




class PhotoViewHolder(
    private val binding: ListItemGalleryBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(galleryItem: GalleryItem) {

//Loading the initial url's from Flickr into my recycler view
        binding.itemImageView.load(galleryItem.url)


//My Onclick Listener that is going to send the data to the next activity
        binding.root.setOnClickListener {



            Toast.makeText(
                binding.root.context,
                "${galleryItem.title}",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}

