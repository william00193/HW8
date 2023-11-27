package com.example.hw8exercise1

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw8exercise1.api.GalleryItem
import com.example.hw8exercise1.databinding.FragmentPhotoGalleryBinding
import com.example.hw8exercise1.databinding.ListItemGalleryBinding
import kotlinx.coroutines.launch



private const val TAG = "PhotoGalleryFragment"



//Page that needs to be fixed and edited for bugs
class PhotoGalleryFragment : Fragment() {

    private var _binding: FragmentPhotoGalleryBinding? = null
    private val binding get() = _binding!!

    private val photoGalleryViewModel: PhotoGalleryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoGalleryBinding.inflate(inflater, container, false)
        binding.photoGrid.layoutManager = GridLayoutManager(context, 3)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photoListAdapter = PhotoListAdapter { item ->
            // Handle item click here if needed
        }

        binding.photoGrid.adapter = photoListAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                photoGalleryViewModel.getPhotos().collect { pagingData ->
                    photoListAdapter.submitData(pagingData)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class PhotoListAdapter(
        private val submitData: (GalleryItem) -> Unit
    ) : RecyclerView.Adapter<PhotoViewHolder>() {

        private var galleryItems: List<GalleryItem> = emptyList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemGalleryBinding.inflate(inflater, parent, false)
            return PhotoViewHolder(binding)
        }

        override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
            val item = galleryItems[position]
            holder.bind(item)
            submitData(item)
        }

        override fun getItemCount(): Int {
            return galleryItems.size
        }

        fun submitData(data: List<GalleryItem>) {
            galleryItems = data
            notifyDataSetChanged()
        }
    }

    inner class PhotoViewHolder(private val binding: ListItemGalleryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GalleryItem) {
            // Bind data to views here
        }
    }
}