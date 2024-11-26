package com.task.pixabay.data.network

import com.task.pixabay.data.model.PixabayImageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApiService {

    // Define an endpoint to get images
    @GET("api/")
    fun getImages(
        @Query("key") key: String,
        @Query("page") page: Int = 1,
    ): Call<PixabayImageResponse> // Returns a call object for fetching the response
}