package com.task.pixabay.presentation.main.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.pixabay.data.model.PixabayImage
import com.task.pixabay.databinding.ListItemImageBinding


/**
 * An adapter for displaying a list of [PixabayImage] objects in a [RecyclerView] using Paging 3.
 * This adapter binds the data for each image item and handles item clicks.
 *
 * @param onItemClick A lambda function that is triggered when an image item is clicked.
 *                    The clicked [PixabayImage] is passed as a parameter.
 */
class ImageAdapter(private val onItemClick: (PixabayImage) -> Unit) :
    PagingDataAdapter<PixabayImage, ImageAdapter.ViewHolder>(PIXABAY_IMAGE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pixabayImage = getItem(position)
        if (pixabayImage != null) {
            holder.bind(pixabayImage)
        }
    }

    inner class ViewHolder(private val binding: ListItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pixabayImage: PixabayImage) {
            binding.pixabayImage = pixabayImage
            binding.root.setOnClickListener {
                onItemClick(pixabayImage) // Handle item click
            }
        }
    }

    companion object {
        private val PIXABAY_IMAGE_COMPARATOR = object : DiffUtil.ItemCallback<PixabayImage>() {
            override fun areItemsTheSame(oldItem: PixabayImage, newItem: PixabayImage): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PixabayImage, newItem: PixabayImage): Boolean {
                return oldItem == newItem
            }
        }
    }
}