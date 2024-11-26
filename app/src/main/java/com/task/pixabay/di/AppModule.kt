package com.task.pixabay.di

import com.task.pixabay.data.network.PixabayApiService
import com.task.pixabay.data.network.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class) // SingletonScope for global app usage
object AppModule {

    @Provides
    fun providePixabayApiService(): PixabayApiService {
        return RetrofitInstance.apiService
    }
}