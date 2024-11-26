package com.task.pixabay.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.task.pixabay.R
import java.text.NumberFormat
import java.util.Locale

object BindingAdapters {

    /**
     * Loads an image from a URL into an ImageView using Glide.
     */
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun bindImage(imageView: AppCompatImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .centerCrop()
            .into(imageView)
    }

    /**
     * Adds "by " before the username.
     */
    @BindingAdapter("user")
    @JvmStatic
    fun bindUser(textView: AppCompatTextView, user: String?) {
        val prefix = textView.context.getString(R.string.by)
        textView.text = "$prefix ${user ?: "Unknown"}"
    }

    /**
     * Adds the "Type:" prefix to the text.
     */
    @BindingAdapter("type")
    @JvmStatic
    fun bindImageType(textView: AppCompatTextView, type: String?) {
        val prefix = textView.context.getString(R.string.label_type)
        textView.text = "$prefix ${type ?: "Unknown"}"
    }

    /**
     * Adds the "Tags:" prefix to the text.
     */
    @BindingAdapter("tags")
    @JvmStatic
    fun bindImageTags(textView: AppCompatTextView, tags: String?) {
        val prefix = textView.context.getString(R.string.label_tags)
        textView.text = "$prefix ${tags ?: "No tags"}"
    }

    /**
     * Formats a large number with commas (e.g., 5,021,192).
     */
    @BindingAdapter("formattedNumber")
    @JvmStatic
    fun bindFormattedNumber(textView: AppCompatTextView, number: Long?) {
        textView.text = number?.let {
            NumberFormat.getNumberInstance(Locale.US).format(it)
        } ?: "0"
    }

    /**
     * Formats image size in MB with one decimal point (e.g., 1.5 MB) and adds the "Size:" prefix to the text.
     */
    @BindingAdapter("formattedSize")
    @JvmStatic
    fun bindFormattedSize(textView: AppCompatTextView, size: Long?) {
        val prefix = textView.context.getString(R.string.label_size)
        textView.text = prefix + " " + {
            size?.let {
                val sizeInMB = it / 1_000_000.0
                String.format(Locale.US, "%.1f MB", sizeInMB)
            } ?: "0.0 MB"
        }
    }
}