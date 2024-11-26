package com.task.pixabay.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PixabayImage(
    val id: Long,
    val type: String?,
    val tags: String?,
    val previewURL: String?,
    val largeImageURL: String?,
    val imageSize: Long?,
    val views: Long?,
    val downloads: Long?,
    val collections: Long?,
    val likes: Long?,
    val comments: Long?,
    val user: String?,
) : Parcelable