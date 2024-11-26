package com.task.pixabay.presentation.main.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.pixabay.data.model.PixabayImage
import com.task.pixabay.databinding.ListItemImageBinding

/**
 * ImageAdapter is a RecyclerView.Adapter implementation for displaying a list of PixabayImage items.
 * It binds each item to the corresponding view and handles item click events.
 *
 * @param pixabayImages A list of PixabayImage objects to be displayed in the RecyclerView.
 * @param onItemClick A lambda function that is triggered when an item is clicked, passing the clicked PixabayImage.
 */
class ImageAdapter(
    private val pixabayImages: List<PixabayImage>,
    private val onItemClick: (PixabayImage) -> Unit,
) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ListItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pixabayImage: PixabayImage) {
            binding.pixabayImage = pixabayImage
            binding.root.setOnClickListener {
                onItemClick(pixabayImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pixabayImages[position])
    }

    override fun getItemCount(): Int = pixabayImages.size
}