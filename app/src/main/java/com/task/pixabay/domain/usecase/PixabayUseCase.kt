package com.task.pixabay.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.task.pixabay.data.model.PixabayImage
import com.task.pixabay.data.repository.PixabayRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case class for fetching a paginated list of [PixabayImage]s from the repository.
 * It abstracts the business logic for the UI layer, calling the repository to get data
 * in a paginated format.
 *
 * @constructor Creates an instance of [PixabayUseCase] with the provided [PixabayRepository].
 * @param repository The repository to fetch images from the API.
 */
class PixabayUseCase @Inject constructor(private val repository: PixabayRepository) {

    fun execute(): Flow<PagingData<PixabayImage>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20, // Number of items per page
                initialLoadSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { repository.getPixabayImages() }
        ).flow
    }
}
