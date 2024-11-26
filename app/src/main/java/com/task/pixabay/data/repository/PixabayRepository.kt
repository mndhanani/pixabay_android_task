package com.task.pixabay.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.task.pixabay.BuildConfig
import com.task.pixabay.data.model.PixabayImage
import com.task.pixabay.data.network.PixabayApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Repository class responsible for interacting with the [PixabayApiService] to fetch
 * data from the Pixabay API and providing it to the use case.
 *
 * @constructor Creates an instance of [PixabayRepository] with the provided [PixabayApiService].
 * @param apiService The [PixabayApiService] used for making network calls to the API.
 */
class PixabayRepository @Inject constructor(private val apiService: PixabayApiService) {

    // PagingSource to load pages of PixabayImage
    class PixabayPagingSource(private val apiService: PixabayApiService) :
        PagingSource<Int, PixabayImage>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PixabayImage> {
            val page = params.key ?: 1
            return try {
                val response = apiService.getImages(
                    key = BuildConfig.API_KEY
                ).execute()

                val images = response.body()?.hits ?: emptyList()

                LoadResult.Page(
                    data = images,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (images.isEmpty()) null else page + 1
                )

            } catch (e: IOException) {
                LoadResult.Error(e)
            } catch (e: HttpException) {
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, PixabayImage>): Int? {
            return state.anchorPosition
        }
    }

    // Function to get PagingSource
    fun getPixabayImages(): PixabayPagingSource {
        return PixabayPagingSource(apiService)
    }
}