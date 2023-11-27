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


class PhotoViewHolder(
    private val binding: ListItemGalleryBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(galleryItem: GalleryItem) {

        binding.itemImageView.load(galleryItem.url)

        binding.root.setOnClickListener {

//            val intent = Intent(it.context, SecondActivity::class.java)
//
//            intent.putExtra("" ,galleryItem.url)
//            intent.putExtra("", galleryItem.title)
//
//            it.context.startActivity(intent)

            Toast.makeText(
                binding.root.context,
                "${galleryItem.title}",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}

