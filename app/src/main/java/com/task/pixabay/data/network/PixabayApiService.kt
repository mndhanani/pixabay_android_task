package com.task.pixabay.data.network

import com.task.pixabay.data.model.PixabayImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApiService {

    // Define an endpoint to get images
    @GET("api/")
    suspend fun getImages(
        @Query("key") apiKey: String,
        @Query("per_page") perPage: Int = 20,
        @Query("page") page: Int = 1,
    ): PixabayImageResponse
}