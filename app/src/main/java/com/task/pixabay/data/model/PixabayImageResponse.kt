package com.task.pixabay.data.model

data class PixabayImageResponse(
    val hits: List<PixabayImage>, // A list of image objects returned from the API
)