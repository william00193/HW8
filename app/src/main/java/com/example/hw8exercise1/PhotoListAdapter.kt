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

//class CrimeListAdapter(
//    private val crimes: List<Crime>,
//    private val onCrimeClicked:(crimeId: UUID)-> Unit): RecyclerView.Adapter<CrimeHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
//        return CrimeHolder(binding)



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

